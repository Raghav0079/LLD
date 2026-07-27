interface RecommendationStrategy {
    void recommend();
}

class TrendingRecomendation implements RecommendationStrategy {
    public void recommend() {
    }
}

class GenreRecommendation implements RecommendationStrategy {
    public void recommend() {
    }
}

class RecentRecommendation implements RecommendationStrategy {
    public void recommend() {
    }
}

class RecommendationAlgorithm {
    private RecommendationStrategy recommendationStrategy;

    public RecommendationAlgorithm(RecommendationStrategy recommendationStrategy) {
        this.recommendationStrategy = recommendationStrategy;
    }
}

public class Main4 {
    public static void main(String[] args) {
        RecommendationStrategy recommendationStrategy = new TrendingRecomendation();
        recommendationStrategy.recommend();
    }
}