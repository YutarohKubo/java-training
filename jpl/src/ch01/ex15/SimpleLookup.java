package ch01.ex15;

import java.util.ArrayList;
import java.util.List;

public class SimpleLookup implements LookupChild{

    private List<String> names = new ArrayList<>();
    private List<Object> values = new ArrayList<>();

    public List<String> getNames() {
        return names;
    }

    public List<Object> getValues() {
        return values;
    }

    @Override
    public void add(String name, Object value) {
        names.add(name);
        values.add(value);
    }

    @Override
    public void remove(String name) {
        int nameIndex = names.indexOf(name);
        if (nameIndex != -1) {
            names.remove(name);
            values.remove(nameIndex);
        }
    }

    @Override
    public Object find(String name) {
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equals(name)) {
                return values.get(i);
            }
        }
        return null;
    }
}
