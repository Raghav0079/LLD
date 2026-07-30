class JudgeAnalytics{
    private static final JudgeAnalytics judgeAnalytics = new JudgeAnalytics();

    private JudgeAnalytics(){

    }
    
    public static JudgeAnalytics getInstance(){
        return judgeAnalytics;
    }
}

public class Main6 {
    public static void main(String[] args) {
        JudgeAnalytics judgeAnalytics = JudgeAnalytics.getInstance();
        JudgeAnalytics judgeAnalytics2 = JudgeAnalytics.getInstance();
        System.out.println(judgeAnalytics);
        System.out.println(judgeAnalytics2);
    }
}