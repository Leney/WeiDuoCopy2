package com.zhongke.weiduo.mvp.model.entity;

/**
 * Created by Karma on 2017/6/15.
 * 类描述：
 */

public class TestBus extends Entity {

    /**
     * sk : {"temp":"32","wind_direction":"东风","wind_strength":"3级","humidity":"63%","time":"15:06"}
     * today : {"temperature":"25℃~32℃","weather":"中雨转雷阵雨","weather_id":{"fa":"08","fb":"04"},"wind":"微风","week":"星期四","city":"广州","date_y":"2017年06月15日","dressing_index":"热","dressing_advice":"天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。","uv_index":"弱","comfort_index":"","wash_index":"不宜","travel_index":"较不宜","exercise_index":"较不宜","drying_index":""}
     * future : {"day_20170615":{"temperature":"25℃~32℃","weather":"中雨转雷阵雨","weather_id":{"fa":"08","fb":"04"},"wind":"微风","week":"星期四","date":"20170615"},"day_20170616":{"temperature":"24℃~31℃","weather":"大雨-暴雨","weather_id":{"fa":"23","fb":"23"},"wind":"微风","week":"星期五","date":"20170616"},"day_20170617":{"temperature":"25℃~30℃","weather":"阵雨","weather_id":{"fa":"03","fb":"03"},"wind":"微风","week":"星期六","date":"20170617"},"day_20170618":{"temperature":"26℃~32℃","weather":"阵雨","weather_id":{"fa":"03","fb":"03"},"wind":"微风","week":"星期日","date":"20170618"},"day_20170619":{"temperature":"26℃~33℃","weather":"阵雨转中雨-大雨","weather_id":{"fa":"03","fb":"22"},"wind":"微风","week":"星期一","date":"20170619"},"day_20170620":{"temperature":"25℃~30℃","weather":"阵雨","weather_id":{"fa":"03","fb":"03"},"wind":"微风","week":"星期二","date":"20170620"},"day_20170621":{"temperature":"26℃~32℃","weather":"阵雨","weather_id":{"fa":"03","fb":"03"},"wind":"微风","week":"星期三","date":"20170621"}}
     */

    private SkBean sk;
    private TodayBean today;
    private FutureBean future;

    public SkBean getSk() {
        return sk;
    }

    public void setSk(SkBean sk) {
        this.sk = sk;
    }

    public TodayBean getToday() {
        return today;
    }

    public void setToday(TodayBean today) {
        this.today = today;
    }

    public FutureBean getFuture() {
        return future;
    }

    public void setFuture(FutureBean future) {
        this.future = future;
    }

    public static class SkBean {
        /**
         * temp : 32
         * wind_direction : 东风
         * wind_strength : 3级
         * humidity : 63%
         * time : 15:06
         */

        private String temp;
        private String wind_direction;
        private String wind_strength;
        private String humidity;
        private String time;

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getWind_direction() {
            return wind_direction;
        }

        public void setWind_direction(String wind_direction) {
            this.wind_direction = wind_direction;
        }

        public String getWind_strength() {
            return wind_strength;
        }

        public void setWind_strength(String wind_strength) {
            this.wind_strength = wind_strength;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    public static class TodayBean {
        /**
         * temperature : 25℃~32℃
         * weather : 中雨转雷阵雨
         * weather_id : {"fa":"08","fb":"04"}
         * wind : 微风
         * week : 星期四
         * city : 广州
         * date_y : 2017年06月15日
         * dressing_index : 热
         * dressing_advice : 天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。
         * uv_index : 弱
         * comfort_index :
         * wash_index : 不宜
         * travel_index : 较不宜
         * exercise_index : 较不宜
         * drying_index :
         */

        private String temperature;
        private String weather;
        private WeatherIdBean weather_id;
        private String wind;
        private String week;
        private String city;
        private String date_y;
        private String dressing_index;
        private String dressing_advice;
        private String uv_index;
        private String comfort_index;
        private String wash_index;
        private String travel_index;
        private String exercise_index;
        private String drying_index;

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public WeatherIdBean getWeather_id() {
            return weather_id;
        }

        public void setWeather_id(WeatherIdBean weather_id) {
            this.weather_id = weather_id;
        }

        public String getWind() {
            return wind;
        }

        public void setWind(String wind) {
            this.wind = wind;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDate_y() {
            return date_y;
        }

        public void setDate_y(String date_y) {
            this.date_y = date_y;
        }

        public String getDressing_index() {
            return dressing_index;
        }

        public void setDressing_index(String dressing_index) {
            this.dressing_index = dressing_index;
        }

        public String getDressing_advice() {
            return dressing_advice;
        }

        public void setDressing_advice(String dressing_advice) {
            this.dressing_advice = dressing_advice;
        }

        public String getUv_index() {
            return uv_index;
        }

        public void setUv_index(String uv_index) {
            this.uv_index = uv_index;
        }

        public String getComfort_index() {
            return comfort_index;
        }

        public void setComfort_index(String comfort_index) {
            this.comfort_index = comfort_index;
        }

        public String getWash_index() {
            return wash_index;
        }

        public void setWash_index(String wash_index) {
            this.wash_index = wash_index;
        }

        public String getTravel_index() {
            return travel_index;
        }

        public void setTravel_index(String travel_index) {
            this.travel_index = travel_index;
        }

        public String getExercise_index() {
            return exercise_index;
        }

        public void setExercise_index(String exercise_index) {
            this.exercise_index = exercise_index;
        }

        public String getDrying_index() {
            return drying_index;
        }

        public void setDrying_index(String drying_index) {
            this.drying_index = drying_index;
        }

        public static class WeatherIdBean {
            /**
             * fa : 08
             * fb : 04
             */

            private String fa;
            private String fb;

            public String getFa() {
                return fa;
            }

            public void setFa(String fa) {
                this.fa = fa;
            }

            public String getFb() {
                return fb;
            }

            public void setFb(String fb) {
                this.fb = fb;
            }
        }
    }

    public static class FutureBean {
        /**
         * day_20170615 : {"temperature":"25℃~32℃","weather":"中雨转雷阵雨","weather_id":{"fa":"08","fb":"04"},"wind":"微风","week":"星期四","date":"20170615"}
         * day_20170616 : {"temperature":"24℃~31℃","weather":"大雨-暴雨","weather_id":{"fa":"23","fb":"23"},"wind":"微风","week":"星期五","date":"20170616"}
         * day_20170617 : {"temperature":"25℃~30℃","weather":"阵雨","weather_id":{"fa":"03","fb":"03"},"wind":"微风","week":"星期六","date":"20170617"}
         * day_20170618 : {"temperature":"26℃~32℃","weather":"阵雨","weather_id":{"fa":"03","fb":"03"},"wind":"微风","week":"星期日","date":"20170618"}
         * day_20170619 : {"temperature":"26℃~33℃","weather":"阵雨转中雨-大雨","weather_id":{"fa":"03","fb":"22"},"wind":"微风","week":"星期一","date":"20170619"}
         * day_20170620 : {"temperature":"25℃~30℃","weather":"阵雨","weather_id":{"fa":"03","fb":"03"},"wind":"微风","week":"星期二","date":"20170620"}
         * day_20170621 : {"temperature":"26℃~32℃","weather":"阵雨","weather_id":{"fa":"03","fb":"03"},"wind":"微风","week":"星期三","date":"20170621"}
         */

        private Day20170615Bean day_20170615;
        private Day20170616Bean day_20170616;
        private Day20170617Bean day_20170617;
        private Day20170618Bean day_20170618;
        private Day20170619Bean day_20170619;
        private Day20170620Bean day_20170620;
        private Day20170621Bean day_20170621;

        public Day20170615Bean getDay_20170615() {
            return day_20170615;
        }

        public void setDay_20170615(Day20170615Bean day_20170615) {
            this.day_20170615 = day_20170615;
        }

        public Day20170616Bean getDay_20170616() {
            return day_20170616;
        }

        public void setDay_20170616(Day20170616Bean day_20170616) {
            this.day_20170616 = day_20170616;
        }

        public Day20170617Bean getDay_20170617() {
            return day_20170617;
        }

        public void setDay_20170617(Day20170617Bean day_20170617) {
            this.day_20170617 = day_20170617;
        }

        public Day20170618Bean getDay_20170618() {
            return day_20170618;
        }

        public void setDay_20170618(Day20170618Bean day_20170618) {
            this.day_20170618 = day_20170618;
        }

        public Day20170619Bean getDay_20170619() {
            return day_20170619;
        }

        public void setDay_20170619(Day20170619Bean day_20170619) {
            this.day_20170619 = day_20170619;
        }

        public Day20170620Bean getDay_20170620() {
            return day_20170620;
        }

        public void setDay_20170620(Day20170620Bean day_20170620) {
            this.day_20170620 = day_20170620;
        }

        public Day20170621Bean getDay_20170621() {
            return day_20170621;
        }

        public void setDay_20170621(Day20170621Bean day_20170621) {
            this.day_20170621 = day_20170621;
        }

        public static class Day20170615Bean {
            /**
             * temperature : 25℃~32℃
             * weather : 中雨转雷阵雨
             * weather_id : {"fa":"08","fb":"04"}
             * wind : 微风
             * week : 星期四
             * date : 20170615
             */

            private String temperature;
            private String weather;
            private WeatherIdBeanX weather_id;
            private String wind;
            private String week;
            private String date;

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public WeatherIdBeanX getWeather_id() {
                return weather_id;
            }

            public void setWeather_id(WeatherIdBeanX weather_id) {
                this.weather_id = weather_id;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public static class WeatherIdBeanX {
                /**
                 * fa : 08
                 * fb : 04
                 */

                private String fa;
                private String fb;

                public String getFa() {
                    return fa;
                }

                public void setFa(String fa) {
                    this.fa = fa;
                }

                public String getFb() {
                    return fb;
                }

                public void setFb(String fb) {
                    this.fb = fb;
                }
            }
        }

        public static class Day20170616Bean {
            /**
             * temperature : 24℃~31℃
             * weather : 大雨-暴雨
             * weather_id : {"fa":"23","fb":"23"}
             * wind : 微风
             * week : 星期五
             * date : 20170616
             */

            private String temperature;
            private String weather;
            private WeatherIdBeanXX weather_id;
            private String wind;
            private String week;
            private String date;

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public WeatherIdBeanXX getWeather_id() {
                return weather_id;
            }

            public void setWeather_id(WeatherIdBeanXX weather_id) {
                this.weather_id = weather_id;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public static class WeatherIdBeanXX {
                /**
                 * fa : 23
                 * fb : 23
                 */

                private String fa;
                private String fb;

                public String getFa() {
                    return fa;
                }

                public void setFa(String fa) {
                    this.fa = fa;
                }

                public String getFb() {
                    return fb;
                }

                public void setFb(String fb) {
                    this.fb = fb;
                }
            }
        }

        public static class Day20170617Bean {
            /**
             * temperature : 25℃~30℃
             * weather : 阵雨
             * weather_id : {"fa":"03","fb":"03"}
             * wind : 微风
             * week : 星期六
             * date : 20170617
             */

            private String temperature;
            private String weather;
            private WeatherIdBeanXXX weather_id;
            private String wind;
            private String week;
            private String date;

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public WeatherIdBeanXXX getWeather_id() {
                return weather_id;
            }

            public void setWeather_id(WeatherIdBeanXXX weather_id) {
                this.weather_id = weather_id;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public static class WeatherIdBeanXXX {
                /**
                 * fa : 03
                 * fb : 03
                 */

                private String fa;
                private String fb;

                public String getFa() {
                    return fa;
                }

                public void setFa(String fa) {
                    this.fa = fa;
                }

                public String getFb() {
                    return fb;
                }

                public void setFb(String fb) {
                    this.fb = fb;
                }
            }
        }

        public static class Day20170618Bean {
            /**
             * temperature : 26℃~32℃
             * weather : 阵雨
             * weather_id : {"fa":"03","fb":"03"}
             * wind : 微风
             * week : 星期日
             * date : 20170618
             */

            private String temperature;
            private String weather;
            private WeatherIdBeanXXXX weather_id;
            private String wind;
            private String week;
            private String date;

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public WeatherIdBeanXXXX getWeather_id() {
                return weather_id;
            }

            public void setWeather_id(WeatherIdBeanXXXX weather_id) {
                this.weather_id = weather_id;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public static class WeatherIdBeanXXXX {
                /**
                 * fa : 03
                 * fb : 03
                 */

                private String fa;
                private String fb;

                public String getFa() {
                    return fa;
                }

                public void setFa(String fa) {
                    this.fa = fa;
                }

                public String getFb() {
                    return fb;
                }

                public void setFb(String fb) {
                    this.fb = fb;
                }
            }

            @Override
            public String toString() {
                return "Day20170618Bean{" +
                        "temperature='" + temperature + '\'' +
                        ", weather='" + weather + '\'' +
                        ", weather_id=" + weather_id +
                        ", wind='" + wind + '\'' +
                        ", week='" + week + '\'' +
                        ", date='" + date + '\'' +
                        '}';
            }
        }

        public static class Day20170619Bean {
            /**
             * temperature : 26℃~33℃
             * weather : 阵雨转中雨-大雨
             * weather_id : {"fa":"03","fb":"22"}
             * wind : 微风
             * week : 星期一
             * date : 20170619
             */

            private String temperature;
            private String weather;
            private WeatherIdBeanXXXXX weather_id;
            private String wind;
            private String week;
            private String date;

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public WeatherIdBeanXXXXX getWeather_id() {
                return weather_id;
            }

            public void setWeather_id(WeatherIdBeanXXXXX weather_id) {
                this.weather_id = weather_id;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public static class WeatherIdBeanXXXXX {
                /**
                 * fa : 03
                 * fb : 22
                 */

                private String fa;
                private String fb;

                public String getFa() {
                    return fa;
                }

                public void setFa(String fa) {
                    this.fa = fa;
                }

                public String getFb() {
                    return fb;
                }

                public void setFb(String fb) {
                    this.fb = fb;
                }
            }

            @Override
            public String toString() {
                return "Day20170619Bean{" +
                        "temperature='" + temperature + '\'' +
                        ", weather='" + weather + '\'' +
                        ", weather_id=" + weather_id +
                        ", wind='" + wind + '\'' +
                        ", week='" + week + '\'' +
                        ", date='" + date + '\'' +
                        '}';
            }
        }

        public static class Day20170620Bean {
            /**
             * temperature : 25℃~30℃
             * weather : 阵雨
             * weather_id : {"fa":"03","fb":"03"}
             * wind : 微风
             * week : 星期二
             * date : 20170620
             */

            private String temperature;
            private String weather;
            private WeatherIdBeanXXXXXX weather_id;
            private String wind;
            private String week;
            private String date;

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public WeatherIdBeanXXXXXX getWeather_id() {
                return weather_id;
            }

            public void setWeather_id(WeatherIdBeanXXXXXX weather_id) {
                this.weather_id = weather_id;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public static class WeatherIdBeanXXXXXX {
                /**
                 * fa : 03
                 * fb : 03
                 */

                private String fa;
                private String fb;

                public String getFa() {
                    return fa;
                }

                public void setFa(String fa) {
                    this.fa = fa;
                }

                public String getFb() {
                    return fb;
                }

                public void setFb(String fb) {
                    this.fb = fb;
                }
            }

            @Override
            public String toString() {
                return "Day20170620Bean{" +
                        "temperature='" + temperature + '\'' +
                        ", weather='" + weather + '\'' +
                        ", weather_id=" + weather_id +
                        ", wind='" + wind + '\'' +
                        ", week='" + week + '\'' +
                        ", date='" + date + '\'' +
                        '}';
            }
        }

        public static class Day20170621Bean {
            /**
             * temperature : 26℃~32℃
             * weather : 阵雨
             * weather_id : {"fa":"03","fb":"03"}
             * wind : 微风
             * week : 星期三
             * date : 20170621
             */

            private String temperature;
            private String weather;
            private WeatherIdBeanXXXXXXX weather_id;
            private String wind;
            private String week;
            private String date;

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public WeatherIdBeanXXXXXXX getWeather_id() {
                return weather_id;
            }

            public void setWeather_id(WeatherIdBeanXXXXXXX weather_id) {
                this.weather_id = weather_id;
            }

            public String getWind() {
                return wind;
            }

            public void setWind(String wind) {
                this.wind = wind;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public static class WeatherIdBeanXXXXXXX {
                /**
                 * fa : 03
                 * fb : 03
                 */

                private String fa;
                private String fb;

                public String getFa() {
                    return fa;
                }

                public void setFa(String fa) {
                    this.fa = fa;
                }

                public String getFb() {
                    return fb;
                }

                public void setFb(String fb) {
                    this.fb = fb;
                }
            }
        }

        @Override
        public String toString() {
            return "FutureBean{" +
                    "day_20170615=" + day_20170615 +
                    ", day_20170616=" + day_20170616 +
                    ", day_20170617=" + day_20170617 +
                    ", day_20170618=" + day_20170618 +
                    ", day_20170619=" + day_20170619 +
                    ", day_20170620=" + day_20170620 +
                    ", day_20170621=" + day_20170621 +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "TestBus{" +
                "sk=" + sk +
                ", today=" + today +
                ", future=" + future +
                '}';
    }
}
