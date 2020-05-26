package com.javabom.lotto.domain.ticket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTicketTest {

    @DisplayName("티켓 생성 후 입력 번호 리스트가 제대로 나오는지 확인")
    @Test
    void getNumbers() {
        // given
        List<Integer> expectedIntegers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<LottoNumber> stubLottoNumbers = expectedIntegers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        LottoTicket lottoTicket = new LottoTicket(stubLottoNumbers);

        // then
        assertThat(lottoTicket.getNumbers()).isEqualTo(expectedIntegers);
    }

    @DisplayName("티켓내에 특정 번호가 들어있는지 확인")
    @CsvSource({"1,true", "7,false"})
    @ParameterizedTest
    void isContain(int matchNumber, boolean isNumberContain) {
        // given
        List<LottoNumber> stubLottoNumbers = IntStream.rangeClosed(1, 6)
                .boxed()
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        LottoTicket lottoTicket = new LottoTicket(stubLottoNumbers);
        LottoNumber matchLottoNumber = new LottoNumber(matchNumber);
        // then
        assertThat(lottoTicket.isContain(matchLottoNumber)).isEqualTo(isNumberContain);
    }

    @DisplayName("로또 티켓생성시 로또번호의 개수는 6개이여야 함.")
    @CsvSource({"5", "7"})
    @ParameterizedTest
    void validateLottoNumbersSize(int size) {
        // given
        List<LottoNumber> stubLottoNumbers = IntStream.rangeClosed(1, size)
                .boxed()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        // then
        assertThatThrownBy(() -> new LottoTicket(stubLottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 티켓에 6개 숫자를 넣어야 합니다.");
    }
}