package com.laputa.demo.app;

/**
 * Created by jiangdongping on 2018/3/27 0027.
 */
public class RefTestObj {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString() + "[id=" + this.id + "]";
    }

    @Override
    protected void finalize() {
        System.out.println("执行析构函数 Object [" + this.hashCode() + "][ id=" + this.id + "] come into finalize");
        try {
            super.finalize();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
