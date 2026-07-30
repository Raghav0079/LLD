class JudgeAnalytics{
    private int run = 0;
    private int submit = 0;
    public void countRun(){
        run++;
    }
    public void countSubmit(){
        submit++;
    }
    public int getRunCount(){
        return run;
    }
    public int getSubmitCount(){
        return submit;
    }
}

public class Main5{
    public static void main(String[] args) {
        JudgeAnalytics judgeAnalytics = new JudgeAnalytics();
        judgeAnalytics.countRun();
        judgeAnalytics.countSubmit();

        JudgeAnalytics judgeAnalytics2 = new JudgeAnalytics();
        judgeAnalytics2.countRun();
    }
}