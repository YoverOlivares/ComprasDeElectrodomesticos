package unc.edu.pe.comprasdeelectrodomesticos.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import unc.edu.pe.comprasdeelectrodomesticos.R;
import unc.edu.pe.comprasdeelectrodomesticos.domain.model.Gift;

public class GiftFactory {

    private final List<Gift> availableGifts;

    public GiftFactory() {
        availableGifts = new ArrayList<>();
        // Asumiendo que los drawables existen según tu estructura de archivos
        availableGifts.add(new Gift("Set de tazas x 6 und", R.drawable.gift_tazas));
        availableGifts.add(new Gift("Individuales x 12 und", R.drawable.gift_individuales));
        availableGifts.add(new Gift("Bowls x 6 und", R.drawable.gift_bowls));
        availableGifts.add(new Gift("Set de vasos x 6 und", R.drawable.gift_vasos));
        availableGifts.add(new Gift("Cucharitas de té x 12 und", R.drawable.gift_cucharitas));
    }

    public Gift createRandomGift() {
        Random random = new Random();
        int index = random.nextInt(availableGifts.size());
        return availableGifts.get(index);
    }
}