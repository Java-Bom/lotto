package com.javabom.lotto.domain.result;

import com.javabom.lotto.domain.shop.UserMoney;

public class WinningStatistics {

    private final LottoResult results;

    public WinningStatistics(LottoResult results) {
        this.results = results;
    }

    private long calculateRevenue() {
        long revenue = 0;
        for (LottoRank result : results.getResults()) {
            revenue += result.getPrizeMoney();
        }
        return revenue;
    }

    public int calculateProfitRatio(UserMoney userMoney) {
        long revenue = calculateRevenue();
        int money = userMoney.getMoney();
        return (int) ((revenue / (double) money) * 100);
    }

    public LottoResult getResults() {
        return results;
    }
}
