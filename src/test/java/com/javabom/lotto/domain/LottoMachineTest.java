package com.javabom.lotto.domain;

import com.javabom.lotto.domain.ticket.LottoTicket;
import com.javabom.lotto.domain.vo.LottoMoney;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoMachineTest {

    @DisplayName("구입 금액에 맞는 로또 티켓을 생성한다.")
    @ParameterizedTest
    @CsvSource({"1000, 1", "3500, 3", "14000, 14"})
    void purchaseLottoTicket(int purchaseAmount, int ticketAmount) {
        LottoMoney lottoMoney = new LottoMoney(purchaseAmount);
        LottoBill lottoBill = LottoMachine.purchaseLottoTicket(lottoMoney);

        assertThat(lottoBill.getAllTickets().size()).isEqualTo(ticketAmount);
    }
}
