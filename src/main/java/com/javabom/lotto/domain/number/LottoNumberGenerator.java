package com.javabom.lotto.domain.number;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.javabom.lotto.domain.number.LottoNumber.LOTTO_NUMBER_UNDER_BOUND;
import static com.javabom.lotto.domain.number.LottoNumber.LOTTO_NUMBER_UPPER_BOUND;

public class LottoNumberGenerator {
    private static final int FIRST_ELEMENT = 0;

    public static Set<LottoNumber> generateRandomNumber(int size, NumberGenerator numberGenerator) {
        List<Integer> numbers = numberGenerator.generate(LOTTO_NUMBER_UNDER_BOUND, LOTTO_NUMBER_UPPER_BOUND);

        return convertIntegerToLottoNumber(numbers.subList(FIRST_ELEMENT, size));
    }

    public static Set<LottoNumber> generateFixedNumber(List<Integer> numbers) {
        return convertIntegerToLottoNumber(numbers);
    }

    private static Set<LottoNumber> convertIntegerToLottoNumber(List<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toCollection(TreeSet::new));
    }
}
