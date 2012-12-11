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

import java.util.Date;


public class VariantBuilder {
  private String name;
  private long startTime = 0;
  private long endTime = Long.MAX_VALUE;
  private int[] spreading = { 1 };

  public VariantBuilder setName(String name) {
    this.name = name;
    return this;
  }

  public VariantBuilder setStartDate(Date startDate) {
    startTime = startDate.getTime();
    return this;
  }

  public VariantBuilder setStartTime(long startTime) {
    this.startTime = startTime;
    return this;
  }

  public VariantBuilder setEndDate(Date endDate) {
    endTime = endDate.getTime();
    return this;
  }

  public VariantBuilder setEndDate(long endTime) {
    this.endTime = endTime;
    return this;
  }

  public VariantBuilder setSpreading(int[] spreading) {
    this.spreading = spreading;
    return this;
  }

  public Variant build() {
    return new Variant(name, startTime, endTime, spreading);
  }
}
