package ch08.ex12;


public class TestClass {

    @TestCase(params = "4", expected = "24")
    @TestCase(params = "0", expected = "0")
    int testFunc1(int param) {
        return param * 6;
    }

    @TestCase(params = "4", expected = "32")
    @TestCase(params = "0", expected = "0")
    int testFunc2(Integer param) {
        return param * 8;
    }

    @TestCase(params = "4", expected = "40")
    @TestCase(params = "0", expected = "0")
    Integer testFunc3(Integer param) {
        return param * 10;
    }

}
