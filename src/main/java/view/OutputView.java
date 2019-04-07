package view;

import domain.LottoTicket;
import domain.prize.PrizeGroup;
import domain.bundle.LottoBundle;
import dto.LottoResultDto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static int MIN_RANK = 3;

    public void getBuyList(LottoBundle lottoBundle) {
        List<LottoTicket> lottoTickets = lottoBundle.getTickets();
        String buyAmount = String.format("수동으로 %d개, 자동으로 %d개 구매하였습니다.\n", lottoBundle.getManualAmount(), lottoBundle.getRandomAmount());
        String list = lottoTickets.stream()
                .map(LottoTicket::getListString)
                .collect(Collectors.joining("\n"));
        StringBuilder sb = new StringBuilder();
        sb.append(buyAmount)
                .append(list);
        System.out.println(sb.toString());
    }

    public void getStatistics(LottoResultDto lottoResultDto) {
        String prefix = "당첨 통계\n----------\n";
        String body = getBody(lottoResultDto.getPrizeStat());
        String suffix = String.format("\n총 수익률은 %.2f%% 입니다.", lottoResultDto.getRate());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(prefix)
                .append(body)
                .append(suffix);

        System.out.println(stringBuilder.toString());
    }

    private String getBody(List<PrizeGroup> prizeGroupList) {
        return Arrays.stream(PrizeGroup.values())
                .filter(prizeGroup -> prizeGroup.getCountOfMatch() >= MIN_RANK)
                .map(prizeGroup -> getLine(prizeGroup, prizeGroupList))
                .collect(Collectors.joining("\n"));
    }

    private String getLine(PrizeGroup prizeGroup, List<PrizeGroup> prizeGroupList) {
        long amount = prizeGroupList.stream()
                .filter(oneOfList -> prizeGroup.getMoney() == oneOfList.getMoney())
                .count();

        StringBuilder sb = new StringBuilder();
        sb.append(prizeGroup.getComment())
                .append(" - ")
                .append(amount);

        return sb.toString();
    }

}
