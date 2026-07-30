class JudgeAnalytics{
    private static JudgeAnalytics judgeAnalytics = null;

    private JudgeAnalytics(){

    }
    
    public static synchronized JudgeAnalytics getInstance(){
        if(judgeAnalytics == null){
            judgeAnalytics = new JudgeAnalytics();
        }
        return judgeAnalytics;
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