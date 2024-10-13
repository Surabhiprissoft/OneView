package com.sbi.oneview.utils;

import com.sbi.oneview.network.ResponseModel.LoginWithOtp.CardDetailsItem;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CardDetailsSorter {

    public static Comparator<CardDetailsItem> cardStatusComparator = new Comparator<CardDetailsItem>() {
        @Override
        public int compare(CardDetailsItem card1, CardDetailsItem card2) {
            String status1 = card1.getCardStatus();
            String status2 = card2.getCardStatus();

            // Handle null values for cardStatus
            if (status1 == null && status2 == null) return 0;
            if (status1 == null) return 1;
            if (status2 == null) return -1;

            // Prioritize "Active" over any other status
            if (status1.equals("A") && !status2.equals("A")) {
                return -1;
            } else if (!status1.equals("A") && status2.equals("A")) {
                return 1;
            }

            // If both are "Active" or neither are "Active", keep relative order
            return 0;
        }
    };

}
