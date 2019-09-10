package liujun.test;

public class child extends parent{
    @Override
    public void dosomething() {
        System.out.println("say hello");
    }

    public static void main(String[] args) {
        child child=new child();
        child.invokesomething();
    }
}
