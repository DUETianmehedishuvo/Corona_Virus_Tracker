package com.mehedi.koronaviruscausesTusu.Services;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DayByDayData {

    public static class Timeline {

        @SerializedName("cases")
        @Expose
        private Cases cases;
        @SerializedName("deaths")
        @Expose
        private Deaths deaths;
        @SerializedName("recovered")
        @Expose
        private Recovered recovered;

        public Cases getCases() {
            return cases;
        }

        public void setCases(Cases cases) {
            this.cases = cases;
        }

        public Deaths getDeaths() {
            return deaths;
        }

        public void setDeaths(Deaths deaths) {
            this.deaths = deaths;
        }

        public Recovered getRecovered() {
            return recovered;
        }

        public void setRecovered(Recovered recovered) {
            this.recovered = recovered;
        }

    }

    public static class Recovered {

        @SerializedName("1/22/20")
        @Expose
        private Integer _12220;
        @SerializedName("1/23/20")
        @Expose
        private Integer _12320;
        @SerializedName("1/24/20")
        @Expose
        private Integer _12420;
        @SerializedName("1/25/20")
        @Expose
        private Integer _12520;
        @SerializedName("1/26/20")
        @Expose
        private Integer _12620;
        @SerializedName("1/27/20")
        @Expose
        private Integer _12720;
        @SerializedName("1/28/20")
        @Expose
        private Integer _12820;
        @SerializedName("1/29/20")
        @Expose
        private Integer _12920;
        @SerializedName("1/30/20")
        @Expose
        private Integer _13020;
        @SerializedName("1/31/20")
        @Expose
        private Integer _13120;
        @SerializedName("2/1/20")
        @Expose
        private Integer _2120;
        @SerializedName("2/2/20")
        @Expose
        private Integer _2220;
        @SerializedName("2/3/20")
        @Expose
        private Integer _2320;
        @SerializedName("2/4/20")
        @Expose
        private Integer _2420;
        @SerializedName("2/5/20")
        @Expose
        private Integer _2520;
        @SerializedName("2/6/20")
        @Expose
        private Integer _2620;
        @SerializedName("2/7/20")
        @Expose
        private Integer _2720;
        @SerializedName("2/8/20")
        @Expose
        private Integer _2820;
        @SerializedName("2/9/20")
        @Expose
        private Integer _2920;
        @SerializedName("2/10/20")
        @Expose
        private Integer _21020;
        @SerializedName("2/11/20")
        @Expose
        private Integer _21120;
        @SerializedName("2/12/20")
        @Expose
        private Integer _21220;
        @SerializedName("2/13/20")
        @Expose
        private Integer _21320;
        @SerializedName("2/14/20")
        @Expose
        private Integer _21420;
        @SerializedName("2/15/20")
        @Expose
        private Integer _21520;
        @SerializedName("2/16/20")
        @Expose
        private Integer _21620;
        @SerializedName("2/17/20")
        @Expose
        private Integer _21720;
        @SerializedName("2/18/20")
        @Expose
        private Integer _21820;
        @SerializedName("2/19/20")
        @Expose
        private Integer _21920;
        @SerializedName("2/20/20")
        @Expose
        private Integer _22020;
        @SerializedName("2/21/20")
        @Expose
        private Integer _22120;
        @SerializedName("2/22/20")
        @Expose
        private Integer _22220;
        @SerializedName("2/23/20")
        @Expose
        private Integer _22320;
        @SerializedName("2/24/20")
        @Expose
        private Integer _22420;
        @SerializedName("2/25/20")
        @Expose
        private Integer _22520;
        @SerializedName("2/26/20")
        @Expose
        private Integer _22620;
        @SerializedName("2/27/20")
        @Expose
        private Integer _22720;
        @SerializedName("2/28/20")
        @Expose
        private Integer _22820;
        @SerializedName("2/29/20")
        @Expose
        private Integer _22920;
        @SerializedName("3/1/20")
        @Expose
        private Integer _3120;
        @SerializedName("3/2/20")
        @Expose
        private Integer _3220;
        @SerializedName("3/3/20")
        @Expose
        private Integer _3320;
        @SerializedName("3/4/20")
        @Expose
        private Integer _3420;
        @SerializedName("3/5/20")
        @Expose
        private Integer _3520;
        @SerializedName("3/6/20")
        @Expose
        private Integer _3620;
        @SerializedName("3/7/20")
        @Expose
        private Integer _3720;
        @SerializedName("3/8/20")
        @Expose
        private Integer _3820;
        @SerializedName("3/9/20")
        @Expose
        private Integer _3920;
        @SerializedName("3/10/20")
        @Expose
        private Integer _31020;
        @SerializedName("3/11/20")
        @Expose
        private Integer _31120;
        @SerializedName("3/12/20")
        @Expose
        private Integer _31220;
        @SerializedName("3/13/20")
        @Expose
        private Integer _31320;
        @SerializedName("3/14/20")
        @Expose
        private Integer _31420;
        @SerializedName("3/15/20")
        @Expose
        private Integer _31520;
        @SerializedName("3/16/20")
        @Expose
        private Integer _31620;
        @SerializedName("3/17/20")
        @Expose
        private Integer _31720;
        @SerializedName("3/18/20")
        @Expose
        private Integer _31820;
        @SerializedName("3/19/20")
        @Expose
        private Integer _31920;
        @SerializedName("3/20/20")
        @Expose
        private Integer _32020;
        @SerializedName("3/21/20")
        @Expose
        private Integer _32120;
        @SerializedName("3/22/20")
        @Expose
        private Integer _32220;
        @SerializedName("3/23/20")
        @Expose
        private Integer _32320;
        @SerializedName("3/24/20")
        @Expose
        private Integer _32420;
        @SerializedName("3/25/20")
        @Expose
        private Integer _32520;
        @SerializedName("3/26/20")
        @Expose
        private Integer _32620;
        @SerializedName("3/27/20")
        @Expose
        private Integer _32720;
        @SerializedName("3/28/20")
        @Expose
        private Integer _32820;
        @SerializedName("3/29/20")
        @Expose
        private Integer _32920;
        @SerializedName("3/30/20")
        @Expose
        private Integer _33020;
        @SerializedName("3/31/20")
        @Expose
        private Integer _33120;

        public Integer get12220() {
            return _12220;
        }

        public void set12220(Integer _12220) {
            this._12220 = _12220;
        }

        public Integer get12320() {
            return _12320;
        }

        public void set12320(Integer _12320) {
            this._12320 = _12320;
        }

        public Integer get12420() {
            return _12420;
        }

        public void set12420(Integer _12420) {
            this._12420 = _12420;
        }

        public Integer get12520() {
            return _12520;
        }

        public void set12520(Integer _12520) {
            this._12520 = _12520;
        }

        public Integer get12620() {
            return _12620;
        }

        public void set12620(Integer _12620) {
            this._12620 = _12620;
        }

        public Integer get12720() {
            return _12720;
        }

        public void set12720(Integer _12720) {
            this._12720 = _12720;
        }

        public Integer get12820() {
            return _12820;
        }

        public void set12820(Integer _12820) {
            this._12820 = _12820;
        }

        public Integer get12920() {
            return _12920;
        }

        public void set12920(Integer _12920) {
            this._12920 = _12920;
        }

        public Integer get13020() {
            return _13020;
        }

        public void set13020(Integer _13020) {
            this._13020 = _13020;
        }

        public Integer get13120() {
            return _13120;
        }

        public void set13120(Integer _13120) {
            this._13120 = _13120;
        }

        public Integer get2120() {
            return _2120;
        }

        public void set2120(Integer _2120) {
            this._2120 = _2120;
        }

        public Integer get2220() {
            return _2220;
        }

        public void set2220(Integer _2220) {
            this._2220 = _2220;
        }

        public Integer get2320() {
            return _2320;
        }

        public void set2320(Integer _2320) {
            this._2320 = _2320;
        }

        public Integer get2420() {
            return _2420;
        }

        public void set2420(Integer _2420) {
            this._2420 = _2420;
        }

        public Integer get2520() {
            return _2520;
        }

        public void set2520(Integer _2520) {
            this._2520 = _2520;
        }

        public Integer get2620() {
            return _2620;
        }

        public void set2620(Integer _2620) {
            this._2620 = _2620;
        }

        public Integer get2720() {
            return _2720;
        }

        public void set2720(Integer _2720) {
            this._2720 = _2720;
        }

        public Integer get2820() {
            return _2820;
        }

        public void set2820(Integer _2820) {
            this._2820 = _2820;
        }

        public Integer get2920() {
            return _2920;
        }

        public void set2920(Integer _2920) {
            this._2920 = _2920;
        }

        public Integer get21020() {
            return _21020;
        }

        public void set21020(Integer _21020) {
            this._21020 = _21020;
        }

        public Integer get21120() {
            return _21120;
        }

        public void set21120(Integer _21120) {
            this._21120 = _21120;
        }

        public Integer get21220() {
            return _21220;
        }

        public void set21220(Integer _21220) {
            this._21220 = _21220;
        }

        public Integer get21320() {
            return _21320;
        }

        public void set21320(Integer _21320) {
            this._21320 = _21320;
        }

        public Integer get21420() {
            return _21420;
        }

        public void set21420(Integer _21420) {
            this._21420 = _21420;
        }

        public Integer get21520() {
            return _21520;
        }

        public void set21520(Integer _21520) {
            this._21520 = _21520;
        }

        public Integer get21620() {
            return _21620;
        }

        public void set21620(Integer _21620) {
            this._21620 = _21620;
        }

        public Integer get21720() {
            return _21720;
        }

        public void set21720(Integer _21720) {
            this._21720 = _21720;
        }

        public Integer get21820() {
            return _21820;
        }

        public void set21820(Integer _21820) {
            this._21820 = _21820;
        }

        public Integer get21920() {
            return _21920;
        }

        public void set21920(Integer _21920) {
            this._21920 = _21920;
        }

        public Integer get22020() {
            return _22020;
        }

        public void set22020(Integer _22020) {
            this._22020 = _22020;
        }

        public Integer get22120() {
            return _22120;
        }

        public void set22120(Integer _22120) {
            this._22120 = _22120;
        }

        public Integer get22220() {
            return _22220;
        }

        public void set22220(Integer _22220) {
            this._22220 = _22220;
        }

        public Integer get22320() {
            return _22320;
        }

        public void set22320(Integer _22320) {
            this._22320 = _22320;
        }

        public Integer get22420() {
            return _22420;
        }

        public void set22420(Integer _22420) {
            this._22420 = _22420;
        }

        public Integer get22520() {
            return _22520;
        }

        public void set22520(Integer _22520) {
            this._22520 = _22520;
        }

        public Integer get22620() {
            return _22620;
        }

        public void set22620(Integer _22620) {
            this._22620 = _22620;
        }

        public Integer get22720() {
            return _22720;
        }

        public void set22720(Integer _22720) {
            this._22720 = _22720;
        }

        public Integer get22820() {
            return _22820;
        }

        public void set22820(Integer _22820) {
            this._22820 = _22820;
        }

        public Integer get22920() {
            return _22920;
        }

        public void set22920(Integer _22920) {
            this._22920 = _22920;
        }

        public Integer get3120() {
            return _3120;
        }

        public void set3120(Integer _3120) {
            this._3120 = _3120;
        }

        public Integer get3220() {
            return _3220;
        }

        public void set3220(Integer _3220) {
            this._3220 = _3220;
        }

        public Integer get3320() {
            return _3320;
        }

        public void set3320(Integer _3320) {
            this._3320 = _3320;
        }

        public Integer get3420() {
            return _3420;
        }

        public void set3420(Integer _3420) {
            this._3420 = _3420;
        }

        public Integer get3520() {
            return _3520;
        }

        public void set3520(Integer _3520) {
            this._3520 = _3520;
        }

        public Integer get3620() {
            return _3620;
        }

        public void set3620(Integer _3620) {
            this._3620 = _3620;
        }

        public Integer get3720() {
            return _3720;
        }

        public void set3720(Integer _3720) {
            this._3720 = _3720;
        }

        public Integer get3820() {
            return _3820;
        }

        public void set3820(Integer _3820) {
            this._3820 = _3820;
        }

        public Integer get3920() {
            return _3920;
        }

        public void set3920(Integer _3920) {
            this._3920 = _3920;
        }

        public Integer get31020() {
            return _31020;
        }

        public void set31020(Integer _31020) {
            this._31020 = _31020;
        }

        public Integer get31120() {
            return _31120;
        }

        public void set31120(Integer _31120) {
            this._31120 = _31120;
        }

        public Integer get31220() {
            return _31220;
        }

        public void set31220(Integer _31220) {
            this._31220 = _31220;
        }

        public Integer get31320() {
            return _31320;
        }

        public void set31320(Integer _31320) {
            this._31320 = _31320;
        }

        public Integer get31420() {
            return _31420;
        }

        public void set31420(Integer _31420) {
            this._31420 = _31420;
        }

        public Integer get31520() {
            return _31520;
        }

        public void set31520(Integer _31520) {
            this._31520 = _31520;
        }

        public Integer get31620() {
            return _31620;
        }

        public void set31620(Integer _31620) {
            this._31620 = _31620;
        }

        public Integer get31720() {
            return _31720;
        }

        public void set31720(Integer _31720) {
            this._31720 = _31720;
        }

        public Integer get31820() {
            return _31820;
        }

        public void set31820(Integer _31820) {
            this._31820 = _31820;
        }

        public Integer get31920() {
            return _31920;
        }

        public void set31920(Integer _31920) {
            this._31920 = _31920;
        }

        public Integer get32020() {
            return _32020;
        }

        public void set32020(Integer _32020) {
            this._32020 = _32020;
        }

        public Integer get32120() {
            return _32120;
        }

        public void set32120(Integer _32120) {
            this._32120 = _32120;
        }

        public Integer get32220() {
            return _32220;
        }

        public void set32220(Integer _32220) {
            this._32220 = _32220;
        }

        public Integer get32320() {
            return _32320;
        }

        public void set32320(Integer _32320) {
            this._32320 = _32320;
        }

        public Integer get32420() {
            return _32420;
        }

        public void set32420(Integer _32420) {
            this._32420 = _32420;
        }

        public Integer get32520() {
            return _32520;
        }

        public void set32520(Integer _32520) {
            this._32520 = _32520;
        }

        public Integer get32620() {
            return _32620;
        }

        public void set32620(Integer _32620) {
            this._32620 = _32620;
        }

        public Integer get32720() {
            return _32720;
        }

        public void set32720(Integer _32720) {
            this._32720 = _32720;
        }

        public Integer get32820() {
            return _32820;
        }

        public void set32820(Integer _32820) {
            this._32820 = _32820;
        }

        public Integer get32920() {
            return _32920;
        }

        public void set32920(Integer _32920) {
            this._32920 = _32920;
        }

        public Integer get33020() {
            return _33020;
        }

        public void set33020(Integer _33020) {
            this._33020 = _33020;
        }

        public Integer get33120() {
            return _33120;
        }

        public void set33120(Integer _33120) {
            this._33120 = _33120;
        }

    }

    public static class Deaths {

        @SerializedName("1/22/20")
        @Expose
        private Integer _12220;
        @SerializedName("1/23/20")
        @Expose
        private Integer _12320;
        @SerializedName("1/24/20")
        @Expose
        private Integer _12420;
        @SerializedName("1/25/20")
        @Expose
        private Integer _12520;
        @SerializedName("1/26/20")
        @Expose
        private Integer _12620;
        @SerializedName("1/27/20")
        @Expose
        private Integer _12720;
        @SerializedName("1/28/20")
        @Expose
        private Integer _12820;
        @SerializedName("1/29/20")
        @Expose
        private Integer _12920;
        @SerializedName("1/30/20")
        @Expose
        private Integer _13020;
        @SerializedName("1/31/20")
        @Expose
        private Integer _13120;
        @SerializedName("2/1/20")
        @Expose
        private Integer _2120;
        @SerializedName("2/2/20")
        @Expose
        private Integer _2220;
        @SerializedName("2/3/20")
        @Expose
        private Integer _2320;
        @SerializedName("2/4/20")
        @Expose
        private Integer _2420;
        @SerializedName("2/5/20")
        @Expose
        private Integer _2520;
        @SerializedName("2/6/20")
        @Expose
        private Integer _2620;
        @SerializedName("2/7/20")
        @Expose
        private Integer _2720;
        @SerializedName("2/8/20")
        @Expose
        private Integer _2820;
        @SerializedName("2/9/20")
        @Expose
        private Integer _2920;
        @SerializedName("2/10/20")
        @Expose
        private Integer _21020;
        @SerializedName("2/11/20")
        @Expose
        private Integer _21120;
        @SerializedName("2/12/20")
        @Expose
        private Integer _21220;
        @SerializedName("2/13/20")
        @Expose
        private Integer _21320;
        @SerializedName("2/14/20")
        @Expose
        private Integer _21420;
        @SerializedName("2/15/20")
        @Expose
        private Integer _21520;
        @SerializedName("2/16/20")
        @Expose
        private Integer _21620;
        @SerializedName("2/17/20")
        @Expose
        private Integer _21720;
        @SerializedName("2/18/20")
        @Expose
        private Integer _21820;
        @SerializedName("2/19/20")
        @Expose
        private Integer _21920;
        @SerializedName("2/20/20")
        @Expose
        private Integer _22020;
        @SerializedName("2/21/20")
        @Expose
        private Integer _22120;
        @SerializedName("2/22/20")
        @Expose
        private Integer _22220;
        @SerializedName("2/23/20")
        @Expose
        private Integer _22320;
        @SerializedName("2/24/20")
        @Expose
        private Integer _22420;
        @SerializedName("2/25/20")
        @Expose
        private Integer _22520;
        @SerializedName("2/26/20")
        @Expose
        private Integer _22620;
        @SerializedName("2/27/20")
        @Expose
        private Integer _22720;
        @SerializedName("2/28/20")
        @Expose
        private Integer _22820;
        @SerializedName("2/29/20")
        @Expose
        private Integer _22920;
        @SerializedName("3/1/20")
        @Expose
        private Integer _3120;
        @SerializedName("3/2/20")
        @Expose
        private Integer _3220;
        @SerializedName("3/3/20")
        @Expose
        private Integer _3320;
        @SerializedName("3/4/20")
        @Expose
        private Integer _3420;
        @SerializedName("3/5/20")
        @Expose
        private Integer _3520;
        @SerializedName("3/6/20")
        @Expose
        private Integer _3620;
        @SerializedName("3/7/20")
        @Expose
        private Integer _3720;
        @SerializedName("3/8/20")
        @Expose
        private Integer _3820;
        @SerializedName("3/9/20")
        @Expose
        private Integer _3920;
        @SerializedName("3/10/20")
        @Expose
        private Integer _31020;
        @SerializedName("3/11/20")
        @Expose
        private Integer _31120;
        @SerializedName("3/12/20")
        @Expose
        private Integer _31220;
        @SerializedName("3/13/20")
        @Expose
        private Integer _31320;
        @SerializedName("3/14/20")
        @Expose
        private Integer _31420;
        @SerializedName("3/15/20")
        @Expose
        private Integer _31520;
        @SerializedName("3/16/20")
        @Expose
        private Integer _31620;
        @SerializedName("3/17/20")
        @Expose
        private Integer _31720;
        @SerializedName("3/18/20")
        @Expose
        private Integer _31820;
        @SerializedName("3/19/20")
        @Expose
        private Integer _31920;
        @SerializedName("3/20/20")
        @Expose
        private Integer _32020;
        @SerializedName("3/21/20")
        @Expose
        private Integer _32120;
        @SerializedName("3/22/20")
        @Expose
        private Integer _32220;
        @SerializedName("3/23/20")
        @Expose
        private Integer _32320;
        @SerializedName("3/24/20")
        @Expose
        private Integer _32420;
        @SerializedName("3/25/20")
        @Expose
        private Integer _32520;
        @SerializedName("3/26/20")
        @Expose
        private Integer _32620;
        @SerializedName("3/27/20")
        @Expose
        private Integer _32720;
        @SerializedName("3/28/20")
        @Expose
        private Integer _32820;
        @SerializedName("3/29/20")
        @Expose
        private Integer _32920;
        @SerializedName("3/30/20")
        @Expose
        private Integer _33020;
        @SerializedName("3/31/20")
        @Expose
        private Integer _33120;

        public Integer get12220() {
            return _12220;
        }

        public void set12220(Integer _12220) {
            this._12220 = _12220;
        }

        public Integer get12320() {
            return _12320;
        }

        public void set12320(Integer _12320) {
            this._12320 = _12320;
        }

        public Integer get12420() {
            return _12420;
        }

        public void set12420(Integer _12420) {
            this._12420 = _12420;
        }

        public Integer get12520() {
            return _12520;
        }

        public void set12520(Integer _12520) {
            this._12520 = _12520;
        }

        public Integer get12620() {
            return _12620;
        }

        public void set12620(Integer _12620) {
            this._12620 = _12620;
        }

        public Integer get12720() {
            return _12720;
        }

        public void set12720(Integer _12720) {
            this._12720 = _12720;
        }

        public Integer get12820() {
            return _12820;
        }

        public void set12820(Integer _12820) {
            this._12820 = _12820;
        }

        public Integer get12920() {
            return _12920;
        }

        public void set12920(Integer _12920) {
            this._12920 = _12920;
        }

        public Integer get13020() {
            return _13020;
        }

        public void set13020(Integer _13020) {
            this._13020 = _13020;
        }

        public Integer get13120() {
            return _13120;
        }

        public void set13120(Integer _13120) {
            this._13120 = _13120;
        }

        public Integer get2120() {
            return _2120;
        }

        public void set2120(Integer _2120) {
            this._2120 = _2120;
        }

        public Integer get2220() {
            return _2220;
        }

        public void set2220(Integer _2220) {
            this._2220 = _2220;
        }

        public Integer get2320() {
            return _2320;
        }

        public void set2320(Integer _2320) {
            this._2320 = _2320;
        }

        public Integer get2420() {
            return _2420;
        }

        public void set2420(Integer _2420) {
            this._2420 = _2420;
        }

        public Integer get2520() {
            return _2520;
        }

        public void set2520(Integer _2520) {
            this._2520 = _2520;
        }

        public Integer get2620() {
            return _2620;
        }

        public void set2620(Integer _2620) {
            this._2620 = _2620;
        }

        public Integer get2720() {
            return _2720;
        }

        public void set2720(Integer _2720) {
            this._2720 = _2720;
        }

        public Integer get2820() {
            return _2820;
        }

        public void set2820(Integer _2820) {
            this._2820 = _2820;
        }

        public Integer get2920() {
            return _2920;
        }

        public void set2920(Integer _2920) {
            this._2920 = _2920;
        }

        public Integer get21020() {
            return _21020;
        }

        public void set21020(Integer _21020) {
            this._21020 = _21020;
        }

        public Integer get21120() {
            return _21120;
        }

        public void set21120(Integer _21120) {
            this._21120 = _21120;
        }

        public Integer get21220() {
            return _21220;
        }

        public void set21220(Integer _21220) {
            this._21220 = _21220;
        }

        public Integer get21320() {
            return _21320;
        }

        public void set21320(Integer _21320) {
            this._21320 = _21320;
        }

        public Integer get21420() {
            return _21420;
        }

        public void set21420(Integer _21420) {
            this._21420 = _21420;
        }

        public Integer get21520() {
            return _21520;
        }

        public void set21520(Integer _21520) {
            this._21520 = _21520;
        }

        public Integer get21620() {
            return _21620;
        }

        public void set21620(Integer _21620) {
            this._21620 = _21620;
        }

        public Integer get21720() {
            return _21720;
        }

        public void set21720(Integer _21720) {
            this._21720 = _21720;
        }

        public Integer get21820() {
            return _21820;
        }

        public void set21820(Integer _21820) {
            this._21820 = _21820;
        }

        public Integer get21920() {
            return _21920;
        }

        public void set21920(Integer _21920) {
            this._21920 = _21920;
        }

        public Integer get22020() {
            return _22020;
        }

        public void set22020(Integer _22020) {
            this._22020 = _22020;
        }

        public Integer get22120() {
            return _22120;
        }

        public void set22120(Integer _22120) {
            this._22120 = _22120;
        }

        public Integer get22220() {
            return _22220;
        }

        public void set22220(Integer _22220) {
            this._22220 = _22220;
        }

        public Integer get22320() {
            return _22320;
        }

        public void set22320(Integer _22320) {
            this._22320 = _22320;
        }

        public Integer get22420() {
            return _22420;
        }

        public void set22420(Integer _22420) {
            this._22420 = _22420;
        }

        public Integer get22520() {
            return _22520;
        }

        public void set22520(Integer _22520) {
            this._22520 = _22520;
        }

        public Integer get22620() {
            return _22620;
        }

        public void set22620(Integer _22620) {
            this._22620 = _22620;
        }

        public Integer get22720() {
            return _22720;
        }

        public void set22720(Integer _22720) {
            this._22720 = _22720;
        }

        public Integer get22820() {
            return _22820;
        }

        public void set22820(Integer _22820) {
            this._22820 = _22820;
        }

        public Integer get22920() {
            return _22920;
        }

        public void set22920(Integer _22920) {
            this._22920 = _22920;
        }

        public Integer get3120() {
            return _3120;
        }

        public void set3120(Integer _3120) {
            this._3120 = _3120;
        }

        public Integer get3220() {
            return _3220;
        }

        public void set3220(Integer _3220) {
            this._3220 = _3220;
        }

        public Integer get3320() {
            return _3320;
        }

        public void set3320(Integer _3320) {
            this._3320 = _3320;
        }

        public Integer get3420() {
            return _3420;
        }

        public void set3420(Integer _3420) {
            this._3420 = _3420;
        }

        public Integer get3520() {
            return _3520;
        }

        public void set3520(Integer _3520) {
            this._3520 = _3520;
        }

        public Integer get3620() {
            return _3620;
        }

        public void set3620(Integer _3620) {
            this._3620 = _3620;
        }

        public Integer get3720() {
            return _3720;
        }

        public void set3720(Integer _3720) {
            this._3720 = _3720;
        }

        public Integer get3820() {
            return _3820;
        }

        public void set3820(Integer _3820) {
            this._3820 = _3820;
        }

        public Integer get3920() {
            return _3920;
        }

        public void set3920(Integer _3920) {
            this._3920 = _3920;
        }

        public Integer get31020() {
            return _31020;
        }

        public void set31020(Integer _31020) {
            this._31020 = _31020;
        }

        public Integer get31120() {
            return _31120;
        }

        public void set31120(Integer _31120) {
            this._31120 = _31120;
        }

        public Integer get31220() {
            return _31220;
        }

        public void set31220(Integer _31220) {
            this._31220 = _31220;
        }

        public Integer get31320() {
            return _31320;
        }

        public void set31320(Integer _31320) {
            this._31320 = _31320;
        }

        public Integer get31420() {
            return _31420;
        }

        public void set31420(Integer _31420) {
            this._31420 = _31420;
        }

        public Integer get31520() {
            return _31520;
        }

        public void set31520(Integer _31520) {
            this._31520 = _31520;
        }

        public Integer get31620() {
            return _31620;
        }

        public void set31620(Integer _31620) {
            this._31620 = _31620;
        }

        public Integer get31720() {
            return _31720;
        }

        public void set31720(Integer _31720) {
            this._31720 = _31720;
        }

        public Integer get31820() {
            return _31820;
        }

        public void set31820(Integer _31820) {
            this._31820 = _31820;
        }

        public Integer get31920() {
            return _31920;
        }

        public void set31920(Integer _31920) {
            this._31920 = _31920;
        }

        public Integer get32020() {
            return _32020;
        }

        public void set32020(Integer _32020) {
            this._32020 = _32020;
        }

        public Integer get32120() {
            return _32120;
        }

        public void set32120(Integer _32120) {
            this._32120 = _32120;
        }

        public Integer get32220() {
            return _32220;
        }

        public void set32220(Integer _32220) {
            this._32220 = _32220;
        }

        public Integer get32320() {
            return _32320;
        }

        public void set32320(Integer _32320) {
            this._32320 = _32320;
        }

        public Integer get32420() {
            return _32420;
        }

        public void set32420(Integer _32420) {
            this._32420 = _32420;
        }

        public Integer get32520() {
            return _32520;
        }

        public void set32520(Integer _32520) {
            this._32520 = _32520;
        }

        public Integer get32620() {
            return _32620;
        }

        public void set32620(Integer _32620) {
            this._32620 = _32620;
        }

        public Integer get32720() {
            return _32720;
        }

        public void set32720(Integer _32720) {
            this._32720 = _32720;
        }

        public Integer get32820() {
            return _32820;
        }

        public void set32820(Integer _32820) {
            this._32820 = _32820;
        }

        public Integer get32920() {
            return _32920;
        }

        public void set32920(Integer _32920) {
            this._32920 = _32920;
        }

        public Integer get33020() {
            return _33020;
        }

        public void set33020(Integer _33020) {
            this._33020 = _33020;
        }

        public Integer get33120() {
            return _33120;
        }

        public void set33120(Integer _33120) {
            this._33120 = _33120;
        }

    }

    public static class Cases {

        @SerializedName("1/22/20")
        @Expose
        private Integer _12220;
        @SerializedName("1/23/20")
        @Expose
        private Integer _12320;
        @SerializedName("1/24/20")
        @Expose
        private Integer _12420;
        @SerializedName("1/25/20")
        @Expose
        private Integer _12520;
        @SerializedName("1/26/20")
        @Expose
        private Integer _12620;
        @SerializedName("1/27/20")
        @Expose
        private Integer _12720;
        @SerializedName("1/28/20")
        @Expose
        private Integer _12820;
        @SerializedName("1/29/20")
        @Expose
        private Integer _12920;
        @SerializedName("1/30/20")
        @Expose
        private Integer _13020;
        @SerializedName("1/31/20")
        @Expose
        private Integer _13120;
        @SerializedName("2/1/20")
        @Expose
        private Integer _2120;
        @SerializedName("2/2/20")
        @Expose
        private Integer _2220;
        @SerializedName("2/3/20")
        @Expose
        private Integer _2320;
        @SerializedName("2/4/20")
        @Expose
        private Integer _2420;
        @SerializedName("2/5/20")
        @Expose
        private Integer _2520;
        @SerializedName("2/6/20")
        @Expose
        private Integer _2620;
        @SerializedName("2/7/20")
        @Expose
        private Integer _2720;
        @SerializedName("2/8/20")
        @Expose
        private Integer _2820;
        @SerializedName("2/9/20")
        @Expose
        private Integer _2920;
        @SerializedName("2/10/20")
        @Expose
        private Integer _21020;
        @SerializedName("2/11/20")
        @Expose
        private Integer _21120;
        @SerializedName("2/12/20")
        @Expose
        private Integer _21220;
        @SerializedName("2/13/20")
        @Expose
        private Integer _21320;
        @SerializedName("2/14/20")
        @Expose
        private Integer _21420;
        @SerializedName("2/15/20")
        @Expose
        private Integer _21520;
        @SerializedName("2/16/20")
        @Expose
        private Integer _21620;
        @SerializedName("2/17/20")
        @Expose
        private Integer _21720;
        @SerializedName("2/18/20")
        @Expose
        private Integer _21820;
        @SerializedName("2/19/20")
        @Expose
        private Integer _21920;
        @SerializedName("2/20/20")
        @Expose
        private Integer _22020;
        @SerializedName("2/21/20")
        @Expose
        private Integer _22120;
        @SerializedName("2/22/20")
        @Expose
        private Integer _22220;
        @SerializedName("2/23/20")
        @Expose
        private Integer _22320;
        @SerializedName("2/24/20")
        @Expose
        private Integer _22420;
        @SerializedName("2/25/20")
        @Expose
        private Integer _22520;
        @SerializedName("2/26/20")
        @Expose
        private Integer _22620;
        @SerializedName("2/27/20")
        @Expose
        private Integer _22720;
        @SerializedName("2/28/20")
        @Expose
        private Integer _22820;
        @SerializedName("2/29/20")
        @Expose
        private Integer _22920;
        @SerializedName("3/1/20")
        @Expose
        private Integer _3120;
        @SerializedName("3/2/20")
        @Expose
        private Integer _3220;
        @SerializedName("3/3/20")
        @Expose
        private Integer _3320;
        @SerializedName("3/4/20")
        @Expose
        private Integer _3420;
        @SerializedName("3/5/20")
        @Expose
        private Integer _3520;
        @SerializedName("3/6/20")
        @Expose
        private Integer _3620;
        @SerializedName("3/7/20")
        @Expose
        private Integer _3720;
        @SerializedName("3/8/20")
        @Expose
        private Integer _3820;
        @SerializedName("3/9/20")
        @Expose
        private Integer _3920;
        @SerializedName("3/10/20")
        @Expose
        private Integer _31020;
        @SerializedName("3/11/20")
        @Expose
        private Integer _31120;
        @SerializedName("3/12/20")
        @Expose
        private Integer _31220;
        @SerializedName("3/13/20")
        @Expose
        private Integer _31320;
        @SerializedName("3/14/20")
        @Expose
        private Integer _31420;
        @SerializedName("3/15/20")
        @Expose
        private Integer _31520;
        @SerializedName("3/16/20")
        @Expose
        private Integer _31620;
        @SerializedName("3/17/20")
        @Expose
        private Integer _31720;
        @SerializedName("3/18/20")
        @Expose
        private Integer _31820;
        @SerializedName("3/19/20")
        @Expose
        private Integer _31920;
        @SerializedName("3/20/20")
        @Expose
        private Integer _32020;
        @SerializedName("3/21/20")
        @Expose
        private Integer _32120;
        @SerializedName("3/22/20")
        @Expose
        private Integer _32220;
        @SerializedName("3/23/20")
        @Expose
        private Integer _32320;
        @SerializedName("3/24/20")
        @Expose
        private Integer _32420;
        @SerializedName("3/25/20")
        @Expose
        private Integer _32520;
        @SerializedName("3/26/20")
        @Expose
        private Integer _32620;
        @SerializedName("3/27/20")
        @Expose
        private Integer _32720;
        @SerializedName("3/28/20")
        @Expose
        private Integer _32820;
        @SerializedName("3/29/20")
        @Expose
        private Integer _32920;
        @SerializedName("3/30/20")
        @Expose
        private Integer _33020;
        @SerializedName("3/31/20")
        @Expose
        private Integer _33120;

        public Integer get12220() {
            return _12220;
        }

        public void set12220(Integer _12220) {
            this._12220 = _12220;
        }

        public Integer get12320() {
            return _12320;
        }

        public void set12320(Integer _12320) {
            this._12320 = _12320;
        }

        public Integer get12420() {
            return _12420;
        }

        public void set12420(Integer _12420) {
            this._12420 = _12420;
        }

        public Integer get12520() {
            return _12520;
        }

        public void set12520(Integer _12520) {
            this._12520 = _12520;
        }

        public Integer get12620() {
            return _12620;
        }

        public void set12620(Integer _12620) {
            this._12620 = _12620;
        }

        public Integer get12720() {
            return _12720;
        }

        public void set12720(Integer _12720) {
            this._12720 = _12720;
        }

        public Integer get12820() {
            return _12820;
        }

        public void set12820(Integer _12820) {
            this._12820 = _12820;
        }

        public Integer get12920() {
            return _12920;
        }

        public void set12920(Integer _12920) {
            this._12920 = _12920;
        }

        public Integer get13020() {
            return _13020;
        }

        public void set13020(Integer _13020) {
            this._13020 = _13020;
        }

        public Integer get13120() {
            return _13120;
        }

        public void set13120(Integer _13120) {
            this._13120 = _13120;
        }

        public Integer get2120() {
            return _2120;
        }

        public void set2120(Integer _2120) {
            this._2120 = _2120;
        }

        public Integer get2220() {
            return _2220;
        }

        public void set2220(Integer _2220) {
            this._2220 = _2220;
        }

        public Integer get2320() {
            return _2320;
        }

        public void set2320(Integer _2320) {
            this._2320 = _2320;
        }

        public Integer get2420() {
            return _2420;
        }

        public void set2420(Integer _2420) {
            this._2420 = _2420;
        }

        public Integer get2520() {
            return _2520;
        }

        public void set2520(Integer _2520) {
            this._2520 = _2520;
        }

        public Integer get2620() {
            return _2620;
        }

        public void set2620(Integer _2620) {
            this._2620 = _2620;
        }

        public Integer get2720() {
            return _2720;
        }

        public void set2720(Integer _2720) {
            this._2720 = _2720;
        }

        public Integer get2820() {
            return _2820;
        }

        public void set2820(Integer _2820) {
            this._2820 = _2820;
        }

        public Integer get2920() {
            return _2920;
        }

        public void set2920(Integer _2920) {
            this._2920 = _2920;
        }

        public Integer get21020() {
            return _21020;
        }

        public void set21020(Integer _21020) {
            this._21020 = _21020;
        }

        public Integer get21120() {
            return _21120;
        }

        public void set21120(Integer _21120) {
            this._21120 = _21120;
        }

        public Integer get21220() {
            return _21220;
        }

        public void set21220(Integer _21220) {
            this._21220 = _21220;
        }

        public Integer get21320() {
            return _21320;
        }

        public void set21320(Integer _21320) {
            this._21320 = _21320;
        }

        public Integer get21420() {
            return _21420;
        }

        public void set21420(Integer _21420) {
            this._21420 = _21420;
        }

        public Integer get21520() {
            return _21520;
        }

        public void set21520(Integer _21520) {
            this._21520 = _21520;
        }

        public Integer get21620() {
            return _21620;
        }

        public void set21620(Integer _21620) {
            this._21620 = _21620;
        }

        public Integer get21720() {
            return _21720;
        }

        public void set21720(Integer _21720) {
            this._21720 = _21720;
        }

        public Integer get21820() {
            return _21820;
        }

        public void set21820(Integer _21820) {
            this._21820 = _21820;
        }

        public Integer get21920() {
            return _21920;
        }

        public void set21920(Integer _21920) {
            this._21920 = _21920;
        }

        public Integer get22020() {
            return _22020;
        }

        public void set22020(Integer _22020) {
            this._22020 = _22020;
        }

        public Integer get22120() {
            return _22120;
        }

        public void set22120(Integer _22120) {
            this._22120 = _22120;
        }

        public Integer get22220() {
            return _22220;
        }

        public void set22220(Integer _22220) {
            this._22220 = _22220;
        }

        public Integer get22320() {
            return _22320;
        }

        public void set22320(Integer _22320) {
            this._22320 = _22320;
        }

        public Integer get22420() {
            return _22420;
        }

        public void set22420(Integer _22420) {
            this._22420 = _22420;
        }

        public Integer get22520() {
            return _22520;
        }

        public void set22520(Integer _22520) {
            this._22520 = _22520;
        }

        public Integer get22620() {
            return _22620;
        }

        public void set22620(Integer _22620) {
            this._22620 = _22620;
        }

        public Integer get22720() {
            return _22720;
        }

        public void set22720(Integer _22720) {
            this._22720 = _22720;
        }

        public Integer get22820() {
            return _22820;
        }

        public void set22820(Integer _22820) {
            this._22820 = _22820;
        }

        public Integer get22920() {
            return _22920;
        }

        public void set22920(Integer _22920) {
            this._22920 = _22920;
        }

        public Integer get3120() {
            return _3120;
        }

        public void set3120(Integer _3120) {
            this._3120 = _3120;
        }

        public Integer get3220() {
            return _3220;
        }

        public void set3220(Integer _3220) {
            this._3220 = _3220;
        }

        public Integer get3320() {
            return _3320;
        }

        public void set3320(Integer _3320) {
            this._3320 = _3320;
        }

        public Integer get3420() {
            return _3420;
        }

        public void set3420(Integer _3420) {
            this._3420 = _3420;
        }

        public Integer get3520() {
            return _3520;
        }

        public void set3520(Integer _3520) {
            this._3520 = _3520;
        }

        public Integer get3620() {
            return _3620;
        }

        public void set3620(Integer _3620) {
            this._3620 = _3620;
        }

        public Integer get3720() {
            return _3720;
        }

        public void set3720(Integer _3720) {
            this._3720 = _3720;
        }

        public Integer get3820() {
            return _3820;
        }

        public void set3820(Integer _3820) {
            this._3820 = _3820;
        }

        public Integer get3920() {
            return _3920;
        }

        public void set3920(Integer _3920) {
            this._3920 = _3920;
        }

        public Integer get31020() {
            return _31020;
        }

        public void set31020(Integer _31020) {
            this._31020 = _31020;
        }

        public Integer get31120() {
            return _31120;
        }

        public void set31120(Integer _31120) {
            this._31120 = _31120;
        }

        public Integer get31220() {
            return _31220;
        }

        public void set31220(Integer _31220) {
            this._31220 = _31220;
        }

        public Integer get31320() {
            return _31320;
        }

        public void set31320(Integer _31320) {
            this._31320 = _31320;
        }

        public Integer get31420() {
            return _31420;
        }

        public void set31420(Integer _31420) {
            this._31420 = _31420;
        }

        public Integer get31520() {
            return _31520;
        }

        public void set31520(Integer _31520) {
            this._31520 = _31520;
        }

        public Integer get31620() {
            return _31620;
        }

        public void set31620(Integer _31620) {
            this._31620 = _31620;
        }

        public Integer get31720() {
            return _31720;
        }

        public void set31720(Integer _31720) {
            this._31720 = _31720;
        }

        public Integer get31820() {
            return _31820;
        }

        public void set31820(Integer _31820) {
            this._31820 = _31820;
        }

        public Integer get31920() {
            return _31920;
        }

        public void set31920(Integer _31920) {
            this._31920 = _31920;
        }

        public Integer get32020() {
            return _32020;
        }

        public void set32020(Integer _32020) {
            this._32020 = _32020;
        }

        public Integer get32120() {
            return _32120;
        }

        public void set32120(Integer _32120) {
            this._32120 = _32120;
        }

        public Integer get32220() {
            return _32220;
        }

        public void set32220(Integer _32220) {
            this._32220 = _32220;
        }

        public Integer get32320() {
            return _32320;
        }

        public void set32320(Integer _32320) {
            this._32320 = _32320;
        }

        public Integer get32420() {
            return _32420;
        }

        public void set32420(Integer _32420) {
            this._32420 = _32420;
        }

        public Integer get32520() {
            return _32520;
        }

        public void set32520(Integer _32520) {
            this._32520 = _32520;
        }

        public Integer get32620() {
            return _32620;
        }

        public void set32620(Integer _32620) {
            this._32620 = _32620;
        }

        public Integer get32720() {
            return _32720;
        }

        public void set32720(Integer _32720) {
            this._32720 = _32720;
        }

        public Integer get32820() {
            return _32820;
        }

        public void set32820(Integer _32820) {
            this._32820 = _32820;
        }

        public Integer get32920() {
            return _32920;
        }

        public void set32920(Integer _32920) {
            this._32920 = _32920;
        }

        public Integer get33020() {
            return _33020;
        }

        public void set33020(Integer _33020) {
            this._33020 = _33020;
        }

        public Integer get33120() {
            return _33120;
        }

        public void set33120(Integer _33120) {
            this._33120 = _33120;
        }

    }


    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("provinces")
    @Expose
    private List<Object> provinces = null;
    @SerializedName("timeline")
    @Expose
    private Timeline timeline;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Object> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<Object> provinces) {
        this.provinces = provinces;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

}