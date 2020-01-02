package ch16.ex04;

import javax.jws.Oneway;
import java.beans.Transient;

public class AppliedAnnotation {

    @Deprecated
    AppliedAnnotation () {

    }

    @BugsFixed("hehehe")
    public void method1 () {

    }

    @Deprecated
    public void method2() {

    }

    @Transient
    public void method3 () {

    }

    @Oneway
    public void method4 () {

    }

}
