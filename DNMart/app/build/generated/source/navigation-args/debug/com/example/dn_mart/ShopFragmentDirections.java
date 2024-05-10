package com.example.dn_mart;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;

public class ShopFragmentDirections {
  private ShopFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionShopToAftershop() {
    return new ActionOnlyNavDirections(R.id.action_shop_to_aftershop);
  }
}
