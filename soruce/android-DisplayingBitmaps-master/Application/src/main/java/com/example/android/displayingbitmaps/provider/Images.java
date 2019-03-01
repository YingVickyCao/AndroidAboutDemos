/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.displayingbitmaps.provider;

/**
 * Some simple test data to use for this sample app.
 */
public class Images {

    /**
     * This are PicasaWeb URLs and could potentially change. Ideally the PicasaWeb API should be
     * used to fetch the URLs.
     * <p>
     * Credit to Romain Guy for the photos:
     * http://www.curious-creature.org/
     * https://plus.google.com/109538161516040592207/about
     * http://www.flickr.com/photos/romainguy
     */
    public final static String[] imageUrls = new String[]{
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Abstract_1.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Abstract_2.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Abstract_3.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Abstract_4.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Abstract_Shapes_1.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Abstract_Shapes_2.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Chroma_1.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Chroma_2.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Color_Burst_1.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Color_Burst_2.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Color_Burst_3.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Desert_1.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Desert_2.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Desert_3.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Desert_4.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Desert_5.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Desert_6.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Desert_7.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/El_Capitan_1.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/El_Capitan_2.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Flower_1.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Flower_2.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Flower_3.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Flower_4.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Flower_5.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Flower_6.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Flower_7.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Flower_8.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Flower_9.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Flower_10.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/High_Sierra.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Ink_Cloud.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Mojave_Day.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Mojave_Night.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Reflection_1.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Reflection_2.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Reflection_3.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Reflection_4.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Sierra_1.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Sierra_2.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Yosemite_1.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Yosemite_3.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/Yosemite_4.jpg",
    };

    /**
     * This are PicasaWeb thumbnail URLs and could potentially change. Ideally the PicasaWeb API
     * should be used to fetch the URLs.
     * <p>
     * Credit to Romain Guy for the photos:
     * http://www.curious-creature.org/
     * https://plus.google.com/109538161516040592207/about
     * http://www.flickr.com/photos/romainguy
     */
    public final static String[] imageThumbUrls = new String[]{
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Abstract_1.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Abstract_2.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Abstract_3.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Abstract_4.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Abstract_Shapes_1.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Abstract_Shapes_2.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Chroma_1.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Chroma_2.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Color_Burst_1.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Color_Burst_2.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Color_Burst_3.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Desert_1.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Desert_2.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Desert_3.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Desert_4.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Desert_5.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Desert_6.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Desert_7.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/El_Capitan_1.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/El_Capitan_2.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Flower_1.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Flower_2.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Flower_3.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Flower_4.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Flower_5.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Flower_6.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Flower_7.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Flower_8.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Flower_9.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Flower_10.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/High_Sierra.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Ink_Cloud.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Mojave_Day.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Mojave_Night.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Reflection_1.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Reflection_2.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Reflection_3.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Reflection_4.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Sierra_1.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Sierra_2.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Yosemite_1.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Yosemite_3.jpg",
            "https://yingvickycao.github.io/img/resources/desktop_pictures/thumbnails/Yosemite_4.jpg",
    };
}
