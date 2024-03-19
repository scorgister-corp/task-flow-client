package net.scorgister.android.taskflow;

public abstract class RunnableUtil <R> implements Runnable {

    private R[] datas;

    @SafeVarargs
    public RunnableUtil(R ... datas) {
        this.datas = datas;
    }
    public RunnableUtil() {}
    @Override
    public void run() {
        exec(datas);
    }

    public abstract void exec(R ... data);

    public void setDatas(R ... datas) {
        this.datas = datas;
    }
}
