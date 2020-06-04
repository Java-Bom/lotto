package com.javabom.lotto.domain.ticket;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LottoTicket {

    private static final int LOTTO_NUM_PICK_SIZE = 6;

    private final List<LottoNumber> numbers;

    public LottoTicket(List<LottoNumber> numbers) {
        validateLottoNumbers(numbers);
        this.numbers = numbers;
    }

    private void validateLottoNumbers(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_NUM_PICK_SIZE) {
            throw new IllegalArgumentException("로또 티켓에 6개 숫자를 넣어야 합니다. 입력 size : " + numbers.size());
        }
    }

    public int countMatchingNumbers(LottoTicket lottoTicket) {
        return (int) lottoTicket.numbers.stream()
                .filter(this.numbers::contains)
                .count();
    }

    public boolean isContain(LottoNumber number) {
        return numbers.contains(number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoTicket ticket = (LottoTicket) o;
        return Objects.equals(numbers, ticket.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }

    @Override
    public String toString() {
        return String.valueOf(numbers);
    }
}
