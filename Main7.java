class JudgeAnalytics{
    private JudgeAnalytics(){

    }
    private static class Holder{
        private static final JudgeAnalytics judgeAnalytics = new JudgeAnalytics();
    }
    public static JudgeAnalytics getInstance(){
        return Holder.judgeAnalytics;
    }
}

public class Main7 {
    public static void main(String[] args) {
        JudgeAnalytics judgeAnalytics = JudgeAnalytics.getInstance();
        JudgeAnalytics judgeAnalytics2 = JudgeAnalytics.getInstance();
        System.out.println(judgeAnalytics);
        System.out.println(judgeAnalytics2);
    }
}