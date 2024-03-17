package net.scorgister.android.taskflow;

public class Connector {

    private String email, password;

    public Connector(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void connect(Runnable callback) {
        callback.run();
    }
}
