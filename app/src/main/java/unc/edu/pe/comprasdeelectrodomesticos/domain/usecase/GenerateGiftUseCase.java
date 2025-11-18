package unc.edu.pe.comprasdeelectrodomesticos.domain.usecase;

import unc.edu.pe.comprasdeelectrodomesticos.domain.model.Gift;
import unc.edu.pe.comprasdeelectrodomesticos.util.GiftFactory;

public class GenerateGiftUseCase {
    private final GiftFactory giftFactory;

    public GenerateGiftUseCase() {
        this.giftFactory = new GiftFactory();
    }

    public Gift execute() {
        return giftFactory.createRandomGift();
    }
}