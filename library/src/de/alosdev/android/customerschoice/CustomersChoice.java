/*
 * Copyright 2012 Hasan Hosgel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.alosdev.android.customerschoice;

import java.util.HashMap;
import java.util.Random;


public final class CustomersChoice {
  private static CustomersChoice instance;
  private LifeTime lifeTime = LifeTime.Session;
  private HashMap<String, Variant> variants;
  private Random random;

  public enum LifeTime {
    Session, Peristent //Persistent not used in the moment
  }

  private CustomersChoice() {
    variants = new HashMap<String, Variant>();
    random = new Random(System.currentTimeMillis());
  }

  public static int getVariant(final String name) {
    checkInstance();

    return instance.getInternalVariant(name);
  }

  private int getInternalVariant(String name) {
    int choosedVariant = 1;
    Variant variant = instance.variants.get(name);
    final long currentTime = System.currentTimeMillis();
    if ((null != variant) && (variant.start < currentTime) && (variant.end > currentTime)) {
      if (variant.currentVariant < 1) {
        int complete = 0;
        for (int spreadingItem : variant.spreading) {
          complete += spreadingItem;
        }

        int nextInt = random.nextInt(complete);
        for (int i = 1; i <= variant.spreading.length; i++) {
          nextInt -= variant.spreading[i - 1];
          if (nextInt < 1) {
            variant.currentVariant = i;
            break;
          }
        }
      }
      return variant.currentVariant;
    }
    return choosedVariant;
  }

  public static void addVariant(final Variant variant) {
    checkInstance();
    instance.variants.put(variant.name, variant);
  }

  private static void checkInstance() {
    if (null == instance) {
      instance = new CustomersChoice();
    }
  }

  public void setLifeTimeForVariants(LifeTime lifeTime) {
    this.lifeTime = lifeTime;
  }

  public LifeTime getLifeTime() {
    return lifeTime;
  }

}
