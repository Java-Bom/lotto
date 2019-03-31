package dto;

import domain.LottoMachine.LottoMachine;
import domain.LottoMachine.ManualLottoMachine;
import domain.LottoMachine.RandomLottoMachine;
import domain.WinningLotto;
import domain.bundle.LottoBundle;
import org.junit.Test;
import util.PrizeGroup;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoResultDtoTest {

    @Test
    public void 로또_가격_맞는지_확인하기() {
        //given
        int price = 6000;
        int manualAmount = 6;
        int bonus = 45;


        List<Integer> win1 = Arrays.asList(1, 2, 3, 4, 5, 6);

        WinningLotto winningLotto = new WinningLotto(win1, bonus);

        ManualNumberDto manualNumberDto1 = new ManualNumberDto(Arrays.asList(1, 2, 3, 4, 5, 6));    //1등
        ManualNumberDto manualNumberDto2 = new ManualNumberDto(Arrays.asList(3, 4, 5, 6, 7, 8));    //5등
        ManualNumberDto manualNumberDto3 = new ManualNumberDto(Arrays.asList(5, 6, 7, 8, 9, 10));
        ManualNumberDto manualNumberDto4 = new ManualNumberDto(Arrays.asList(7, 8, 9, 10, 11, 12));
        ManualNumberDto manualNumberDto5 = new ManualNumberDto(Arrays.asList(9, 10, 11, 12, 13, 14));
        ManualNumberDto manualNumberDto6 = new ManualNumberDto(Arrays.asList(11, 12, 13, 14, 15, 16));

        List<ManualNumberDto> manualNumberDtos =
                Arrays.asList(manualNumberDto1,
                        manualNumberDto2,
                        manualNumberDto3,
                        manualNumberDto4,
                        manualNumberDto5,
                        manualNumberDto6);

        InputDto inputDto = new InputDto(price, manualAmount, manualNumberDtos);


        //when
        LottoMachine manual = new ManualLottoMachine(inputDto);
        LottoMachine random = new RandomLottoMachine(inputDto);

        LottoBundle lottoBundle = new LottoBundle(manual.buyLotto(), random.buyLotto());
        LottoResultDto lottoResultDto = new LottoResultDto(lottoBundle, winningLotto);

        //then
        assertThat(lottoResultDto.getSum()).isEqualTo(2000050000);
    }

    @Test
    public void 수익률_계산하기() {
        //given
        int price = 6000;
        int manualAmount = 6;
        int bonus = 45;
        double rate = (double) (2000050000 - 6000) / 6000 * 100;

        List<Integer> win1 = Arrays.asList(1, 2, 3, 4, 5, 6);

        WinningLotto winningLotto = new WinningLotto(win1, bonus);

        ManualNumberDto manualNumberDto1 = new ManualNumberDto(Arrays.asList(1, 2, 3, 4, 5, 6));    //1등
        ManualNumberDto manualNumberDto2 = new ManualNumberDto(Arrays.asList(3, 4, 5, 6, 7, 8));    //5등
        ManualNumberDto manualNumberDto3 = new ManualNumberDto(Arrays.asList(5, 6, 7, 8, 9, 10));
        ManualNumberDto manualNumberDto4 = new ManualNumberDto(Arrays.asList(7, 8, 9, 10, 11, 12));
        ManualNumberDto manualNumberDto5 = new ManualNumberDto(Arrays.asList(9, 10, 11, 12, 13, 14));
        ManualNumberDto manualNumberDto6 = new ManualNumberDto(Arrays.asList(11, 12, 13, 14, 15, 16));

        List<ManualNumberDto> manualNumberDtos =
                Arrays.asList(manualNumberDto1,
                        manualNumberDto2,
                        manualNumberDto3,
                        manualNumberDto4,
                        manualNumberDto5,
                        manualNumberDto6);

        InputDto inputDto = new InputDto(price, manualAmount, manualNumberDtos);


        //when
        LottoMachine manual = new ManualLottoMachine(inputDto);
        LottoMachine random = new RandomLottoMachine(inputDto);

        LottoBundle lottoBundle = new LottoBundle(manual.buyLotto(), random.buyLotto());
        LottoResultDto lottoResultDto = new LottoResultDto(lottoBundle, winningLotto);

        //then
        assertThat(lottoResultDto.getRate()).isEqualTo(rate);
    }


    @Test
    public void 우승_로또랑_내가_수동으로_뽑은_로또_비교하기() {
        int bonus = 7;
        int price = 3000;
        int manualAmount = 3;
        List<Integer> winningNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonus);


        ManualNumberDto allMatchNumbers = new ManualNumberDto(Arrays.asList(1, 2, 3, 4, 5, 6));
        ManualNumberDto matchFive = new ManualNumberDto(Arrays.asList(45, 2, 3, 4, 5, 6));
        ManualNumberDto matchFiveWithBonus = new ManualNumberDto(Arrays.asList(1, 2, 3, 4, 5, 7));

        List<ManualNumberDto> manualNumberDtos = Arrays.asList(allMatchNumbers, matchFive, matchFiveWithBonus);

        InputDto inputDto = new InputDto(price, manualAmount, manualNumberDtos);

        LottoMachine manual = new ManualLottoMachine(inputDto);
        LottoMachine random = new RandomLottoMachine(inputDto);

        LottoBundle lottoBundle = new LottoBundle(manual.buyLotto(), random.buyLotto());
        LottoResultDto lottoResultDto = new LottoResultDto(lottoBundle, winningLotto);


        List<PrizeGroup> expect = Arrays.asList(PrizeGroup.FIRST, PrizeGroup.FIRST, PrizeGroup.SECOND, PrizeGroup.THIRD);

        assertThat(lottoResultDto.getPrizeStat()).containsAll(expect);

    }
}