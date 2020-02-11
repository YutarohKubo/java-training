import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class OperationAreaPanel extends InterpretPanel {

    AppFrame frame;
    private SearchAreaPanel searchAreaPanel;
    JPanel panelButtonArea;
    ControlMemberPanel controlMemberPanel;
    JButton buttonModifyVariable;
    JButton buttonExecuteMethod;
    JButton buttonCreateObject;
    JButton buttonCreateArrayMode;
    JButton buttonDisplayDeclaredMember;
    JButton buttonProperty;
    DisplayInsideArrayPanel displayInsideArrayPanel;
    MainAreaPanel mainAreaPanel;

    public OperationAreaPanel(AppFrame frame, ControlMemberPanel controlMemberPanel, MainAreaPanel mainAreaPanel, DisplayInsideArrayPanel displayInsideArrayPanel) {
        this.frame = frame;
        this.controlMemberPanel = controlMemberPanel;
        this.mainAreaPanel = mainAreaPanel;
        this.displayInsideArrayPanel = displayInsideArrayPanel;
        addComponent();
    }

    public void setSearchAreaPanel(SearchAreaPanel searchAreaPanel) {
        this.searchAreaPanel = searchAreaPanel;
    }

    @Override
    void setPanelLayout() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }

    @Override
    void setupComponent() {
        panelButtonArea = new JPanel();
        panelButtonArea.setLayout(new BoxLayout(panelButtonArea, BoxLayout.PAGE_AXIS));
        buttonProperty = new JButton("状態表示");
        buttonProperty.setAlignmentX(0.5f);
        buttonCreateArrayMode = new JButton("配列生成");
        buttonCreateArrayMode.setAlignmentX(0.5f);
        buttonDisplayDeclaredMember = new JButton("宣言一覧表示");
        buttonDisplayDeclaredMember.setAlignmentX(0.5f);
    }

    private void addComponent() {
        this.add(controlMemberPanel);
        buttonProperty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StatusDisplayDialog statusDisplayDialog = new StatusDisplayDialog(frame, displayInsideArrayPanel, controlMemberPanel.getConstructorPanel(), controlMemberPanel.getMethodPanel(), controlMemberPanel.getFieldPanel());
                statusDisplayDialog.setVisible(true);
            }
        });
        buttonCreateArrayMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String targetArrayType = searchAreaPanel.getPackageClassFieldText();
                if (targetArrayType.length() == 0) {
                    JOptionPane.showMessageDialog(frame, "Package+Classを入力してください");
                    return;
                }
                Class<?> targetClazz = null;
                try {
                    targetClazz = Class.forName(targetArrayType);
                } catch (ClassNotFoundException ex) {
                    ConsoleAreaPanel.appendNewLog("Throw ClassNotFoundException.");
                    ex.printStackTrace();
                }
                ArrayLengthSettingDialog arrayLengthSettingDialog = new ArrayLengthSettingDialog(frame, mainAreaPanel, OperationAreaPanel.this, targetClazz);
                arrayLengthSettingDialog.setVisible(true);
            }
        });
        buttonDisplayDeclaredMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeclaredAllMemberDialog allMemberDialog = new DeclaredAllMemberDialog(frame);
                String targetArrayType = searchAreaPanel.getPackageClassFieldText();
                if (targetArrayType.length() == 0) {
                    JOptionPane.showMessageDialog(frame, "Package+Classを入力してください");
                    return;
                }
                Class<?> targetClazz = null;
                try {
                    targetClazz = Class.forName(targetArrayType);
                } catch (ClassNotFoundException ex) {
                    ConsoleAreaPanel.appendNewLog("Throw ClassNotFoundException.");
                    ex.printStackTrace();
                    return;
                }
                allMemberDialog.appendTextToTextArea(clazzPrefix(targetClazz, 0));
                allMemberDialog.appendTextToTextArea(" {\n\n");
                allMemberDialog.appendTextToTextArea(textAllMember(targetClazz, 1));
                for (Class<?> clazz : targetClazz.getDeclaredClasses()) {
                    allMemberDialog.appendTextToTextArea(clazzPrefix(clazz, 1));
                    allMemberDialog.appendTextToTextArea(" {\n\n");
                    allMemberDialog.appendTextToTextArea(textAllMember(clazz, 2));
                    allMemberDialog.appendTextToTextArea(setBlank(1));
                    allMemberDialog.appendTextToTextArea("}\n\n");
                }
                allMemberDialog.appendTextToTextArea(setBlank(0));
                allMemberDialog.appendTextToTextArea("}\n\n");
                allMemberDialog.setVisible(true);
                allMemberDialog.scrollTop();
            }
        });
        panelButtonArea.add(setMargin(buttonProperty, 0, 0, 0, 0));
        panelButtonArea.add(setMargin(buttonCreateArrayMode, 0, 0, 0, 0));
        panelButtonArea.add(setMargin(buttonDisplayDeclaredMember, 0, 0, 0, 0));
        this.add(panelButtonArea);
    }

    private String inspectModifier(int v) {
        StringBuilder sb = new StringBuilder();
        if (Modifier.isPrivate(v)) sb.append("private ");
        if (Modifier.isPublic(v)) sb.append("public ");
        if (Modifier.isProtected(v)) sb.append("protected ");
        if (Modifier.isStatic(v)) sb.append("static ");
        if (Modifier.isAbstract(v)) sb.append("abstract ");
        if (Modifier.isFinal(v)) sb.append("final ");
        if (Modifier.isNative(v)) sb.append("native ");
        if (Modifier.isStrict(v)) sb.append("strict ");
        if (Modifier.isSynchronized(v)) sb.append("synchoronized ");
        if (Modifier.isTransient(v)) sb.append("transient ");
        if (Modifier.isVolatile(v)) sb.append("volatile ");
        return sb.toString();
    }

    private String clazzPrefix(Class<?> clazz, int blank) {
        StringBuilder sb = new StringBuilder();
        sb.append(setBlank(blank));
        sb.append(inspectModifier(clazz.getModifiers()));
        sb.append(judgeModule(clazz));
        sb.append(thisClazz(clazz));
        sb.append(superClazz(clazz));
        sb.append(implementsInterface(clazz));
        return sb.toString();
    }

    private String textAllMember(Class<?> clazz, int blank) {
        StringBuilder sb = new StringBuilder();
        for (Field field : clazz.getDeclaredFields()) {
            for (Annotation annotation : field.getAnnotations()) {
                sb.append(setBlank(blank));
                sb.append(annotation);
                sb.append("\n");
            }
            sb.append(setBlank(blank));
            sb.append(inspectModifier(field.getModifiers()));
            sb.append(setFieldDeclare(field));
            sb.append(" ");
            sb.append(field.getName());
            sb.append("\n\n");
        }
        for (Constructor constructor : clazz.getDeclaredConstructors()) {
            for (Annotation annotation : constructor.getAnnotations()) {
                sb.append(setBlank(blank));
                sb.append(annotation).append("\n");
            }
            sb.append(setBlank(blank));
            sb.append(setConstructorDeclare(constructor));
            sb.append("\n\n");
        }
        for (Method method : clazz.getDeclaredMethods()) {
            for (Annotation annotation : method.getAnnotations()) {
                sb.append(setBlank(blank));
                sb.append(annotation).append("\n");
            }
            sb.append(setBlank(blank));
            sb.append(setMethodDeclare(method));
            sb.append("\n\n");
        }
        return sb.toString();
    }

    private String setFieldDeclare(Field field) {
        StringBuilder sb = new StringBuilder();
        //ジェネリクス型の時
        if (field.getGenericType() instanceof ParameterizedType) {
            sb.append(field.getType().getSimpleName());
            ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
            Type[] types = parameterizedType.getActualTypeArguments();
            if (types.length > 0) {
                sb.append("<");
                for (Type type : types) {
                    sb.append(type.getTypeName());
                    sb.append(", ");
                }
                sb.delete(sb.length() - 2, sb.length());
                sb.append(">");
            }
            return sb.toString();
        }
        //ジェネリクス配列型の時
        if (field.getGenericType() instanceof GenericArrayType) {
            System.out.println("FieldName = " + field.getName());
            GenericArrayType genericArrayType = (GenericArrayType) field.getGenericType();
            //E[]型のとき
            if (genericArrayType.getGenericComponentType() instanceof TypeVariable<?>) {
                TypeVariable<?> typeVariable = (TypeVariable<?>) genericArrayType.getGenericComponentType();
                System.out.println("TypeVariableFieldName = " + field.getName());
                sb.append(typeVariable.getName());
                sb.append("[]");
            }
            //ClazzName<ParaName>[]の時
            else if (genericArrayType.getGenericComponentType() instanceof ParameterizedType) {
                sb.append(field.getType().getSimpleName());
                ParameterizedType parameterizedType = (ParameterizedType) genericArrayType.getGenericComponentType();
                Type[] types = parameterizedType.getActualTypeArguments();
                StringBuilder replaceBuilder = new StringBuilder();
                if (types.length > 0) {
                    replaceBuilder.append("<");
                    for (Type type : types) {
                        replaceBuilder.append(type.getTypeName());
                        replaceBuilder.append(", ");
                    }
                    replaceBuilder.delete(replaceBuilder.length() - 2, sb.length());
                    replaceBuilder.append(">");
                }
                sb = new StringBuilder(sb.toString().replaceFirst("\\[]", replaceBuilder.toString() + "[]"));
            }
            return sb.toString();
        }
        sb.append(field.getType().getSimpleName());
        return sb.toString();
    }

    private String setMethodDeclare(Method method) {
        StringBuilder sb = new StringBuilder();
        sb.append(inspectModifier(method.getModifiers()));
        TypeVariable<?>[] params = method.getTypeParameters();
        if (params.length > 0) {
            sb.append("<");
            for (TypeVariable<?> param : params) {
                sb.append(param.getName());
                sb.append(", ");
            }
            sb.delete(sb.length() - 2, sb.length());
            sb.append(">");
            sb.append(" ");
        }
        sb.append(method.getReturnType().getSimpleName());
        if (params.length > 0) {
            sb.append("<");
            for (TypeVariable<?> param : params) {
                sb.append(param.getName());
                if (param.getBounds().length > 0) {
                    sb.append(" extends ");
                    for (Type typeBound : param.getBounds()) {
                        sb.append(typeBound.getTypeName());
                        sb.append(" & ");
                    }
                    sb.delete(sb.length() - 3, sb.length());
                }
                sb.append(", ");
            }
            sb.delete(sb.length() - 2, sb.length());
            sb.append(">");
        }
        sb.append(" ");
        sb.append(method.getName());
        sb.append("(");
        sb.append(giveMethodArgumentTypes(method));
        sb.append(")");
        return sb.toString();
    }

    private String setConstructorDeclare(Constructor constructor) {
        StringBuilder sb = new StringBuilder();
        sb.append(inspectModifier(constructor.getModifiers()));
        TypeVariable<?>[] params = constructor.getTypeParameters();
        if (params.length > 0) {
            sb.append("<");
            for (TypeVariable<?> param : params) {
                sb.append(param.getName());
                sb.append(", ");
            }
            sb.delete(sb.length() - 2, sb.length());
            sb.append(">");
            sb.append(" ");
        }
        sb.append(constructor.getName());
        sb.append("(");
        sb.append(giveConstructorArgumentTypes(constructor));
        sb.append(")");
        return sb.toString();
    }

    private String setBlank(int blank) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < blank; i++) {
            sb.append("        ");
        }
        return sb.toString();
    }

    private String judgeModule(Class<?> clazz) {
        if (clazz.isAnnotation()) {
            return "@interface ";
        } else if (clazz.isEnum()) {
            return "enum ";
        } else if (clazz.isInterface()) {
            return "interface ";
        } else {
            return "class ";
        }
    }

    private String thisClazz(Class<?> clazz) {
        StringBuilder sb = new StringBuilder();
        sb.append(clazz.getSimpleName());
        TypeVariable<?>[] params = clazz.getTypeParameters();
        if (params.length > 0) {
            sb.append("<");
            for (TypeVariable<?> param : params) {
                sb.append(param.getName());
                sb.append(", ");
            }
            sb.delete(sb.length() - 2, sb.length());
            sb.append(">");
        }
        sb.append(" ");
        return sb.toString();
    }

    private String superClazz(Class<?> clazz) {
        Type superType = clazz.getGenericSuperclass();
        if (superType == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Class<?> cls = null;
        ParameterizedType parameterizedType = null;
        if (superType instanceof Class<?>) {
            cls = (Class<?>) superType;
            if (cls.equals(Object.class)) {
                return "";
            }
        } else if (superType instanceof ParameterizedType) {
            parameterizedType = (ParameterizedType) superType;
            cls = (Class<?>) parameterizedType.getRawType();
        } else {
            throw new Error("Unexpected non-class type");
        }
        sb.append("extends ");
        sb.append(cls.getCanonicalName());
        if (parameterizedType != null && parameterizedType.getActualTypeArguments().length > 0) {
            sb.append("<");
            for (Type type : parameterizedType.getActualTypeArguments()) {
                sb.append(type.getTypeName());
                sb.append(", ");
            }
            sb.delete(sb.length() - 2, sb.length());
            sb.append(">");
            sb.append(" ");
            return sb.toString();
        }
        TypeVariable<?>[] params = cls.getTypeParameters();
        if (params.length > 0) {
            sb.append("<");
            for (TypeVariable<?> param : params) {
                sb.append(param.getName());
                sb.append(", ");
            }
            sb.delete(sb.length() - 2, sb.length());
            sb.append(">");
        }
        sb.append(" ");
        return sb.toString();
    }

    private String implementsInterface(Class<?> clazz) {
        Type[] types = clazz.getGenericInterfaces();
        if (types.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("implements ");
        for (Type type : types) {
            Class<?> cls = null;
            ParameterizedType parameterizedType = null;
            if (type instanceof Class<?>) {
                cls = (Class<?>) type;
            } else if (type instanceof ParameterizedType) {
                parameterizedType = (ParameterizedType) type;
                cls = (Class<?>) parameterizedType.getRawType();
            } else {
                throw new Error("Unexpected non-class type");
            }
            sb.append(cls.getCanonicalName());
            if (parameterizedType != null && parameterizedType.getActualTypeArguments().length > 0) {
                sb.append("<");
                for (Type typeActual : parameterizedType.getActualTypeArguments()) {
                    sb.append(typeActual.getTypeName());
                    sb.append(", ");
                }
                sb.delete(sb.length() - 2, sb.length());
                sb.append(">");
                sb.append(" ");
                return sb.toString();
            }
            TypeVariable<?>[] params = cls.getTypeParameters();
            if (params.length > 0) {
                sb.append("<");
                for (TypeVariable<?> param : params) {
                    sb.append(param.getName());
                    sb.append(", ");
                }
                sb.delete(sb.length() - 2, sb.length());
                sb.append(">");
            }
            sb.append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(" ");
        return sb.toString();
    }

    private String giveMethodArgumentTypes(Method method) {
        Type[] argTypes = method.getGenericParameterTypes();
        if (argTypes.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Type type : argTypes) {
            sb.append(type.getTypeName());
            sb.append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    private String giveConstructorArgumentTypes(Constructor constructor) {
        Type[] argTypes = constructor.getGenericParameterTypes();
        if (argTypes.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Type type : argTypes) {
            sb.append(type.getTypeName());
            sb.append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    public void changeButtonCreateArrayModeToBack() {
        buttonCreateArrayMode.setText("戻る");
        if (buttonCreateArrayMode.getActionListeners().length >= 1) {
            buttonCreateArrayMode.removeActionListener(buttonCreateArrayMode.getActionListeners()[0]);
        }
        buttonCreateArrayMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainAreaPanel.removeArrayPanel();
                changeButtonCreateArrayModeToCreate();
            }
        });
    }

    public void changeButtonCreateArrayModeToCreate() {
        buttonCreateArrayMode.setText("配列生成");
        if (buttonCreateArrayMode.getActionListeners().length >= 1) {
            buttonCreateArrayMode.removeActionListener(buttonCreateArrayMode.getActionListeners()[0]);
        }
        buttonCreateArrayMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String targetArrayType = searchAreaPanel.getPackageClassFieldText();
                if (targetArrayType.length() == 0) {
                    JOptionPane.showMessageDialog(frame, "Package+Classを入力してください");
                    return;
                }
                Class<?> targetClazz = null;
                try {
                    targetClazz = Class.forName(targetArrayType);
                } catch (ClassNotFoundException ex) {
                    ConsoleAreaPanel.appendNewLog("Throw ClassNotFoundException.");
                    ex.printStackTrace();
                }
                ArrayLengthSettingDialog arrayLengthSettingDialog = new ArrayLengthSettingDialog(frame, mainAreaPanel, OperationAreaPanel.this, targetClazz);
                arrayLengthSettingDialog.setVisible(true);
            }
        });
    }
}
