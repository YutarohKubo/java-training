package ch09.ex03;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;

public class Main {

    public void process() throws FileNotFoundException, UnknownHostException {
        try {
            fileNotFoundExceptionProcess();
            unknownHostExceptionProcess();
        } catch (FileNotFoundException | UnknownHostException e) {
            throw e;
        }
    }

    private void fileNotFoundExceptionProcess() throws FileNotFoundException {
        throw new FileNotFoundException();
    }

    private void unknownHostExceptionProcess() throws UnknownHostException {
        throw new UnknownHostException();
    }

}
