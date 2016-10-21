package ru.allmoyki.may.network;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;

import java.util.Map;

import ru.allmoyki.may.pojo.AddOrderPojo;
import ru.allmoyki.may.pojo.AllCarMarkaPojo;
import ru.allmoyki.may.pojo.AllCarwashPojo;
import ru.allmoyki.may.pojo.CancelOrderPojo;
import ru.allmoyki.may.pojo.CarModelsPojo;
import ru.allmoyki.may.pojo.CarwashFeedbacksPojo;
import ru.allmoyki.may.pojo.CheckSMSPojo;
import ru.allmoyki.may.pojo.CurrentCarwashPojo;
import ru.allmoyki.may.pojo.CurrentUserPojo;
import ru.allmoyki.may.pojo.LoginPojo;
import ru.allmoyki.may.pojo.OrderPojo;
import ru.allmoyki.may.pojo.RegionsPojo;
import ru.allmoyki.may.pojo.SearchPojo;
import ru.allmoyki.may.pojo.ServicesPojo;
import ru.allmoyki.may.pojo.TimesPojo;
import ru.allmoyki.may.pojo.UserPojo;

/**
 * Created by Boychuk Dmitriy on 29.01.15.
 */
public class Api {
    static String mainUrl = "http://allmoyki.ru/backend";
    static String getCarwashList = mainUrl + "/CarWash/GetNearest";
    static String getCurrentCarwash = mainUrl + "/CarWash/GetById";
    static String getCarwashFeedbacks = mainUrl + "/CarWashReview/GetAllByCarWash";
    static String getCarMarks = mainUrl + "/CarBrand/GetAll";
    static String getCarModels = mainUrl + "/CarModel/GetAllByCarBrand";
    static String getRegions = mainUrl + "/Region/GetAll";
    static String getRegistration = mainUrl + "/User/SignUpClient";
    static String login = mainUrl + "/User/SignInClient";
    static String getSmsCode = mainUrl + "/User/CheckSMSCode";
    static String addOrder = mainUrl + "/ClientOrder/CustomAdd";
    static String client = mainUrl + "/Client/GetByToken";
    static String times = mainUrl + "/WashTime/GetAll";
    static String services = mainUrl + "/WashService/GetAll";
    static String search = mainUrl + "/CarWash/Search";
    static String orders = mainUrl + "/ClientOrder/GetAllByClient";
    static String cancelOrder = mainUrl + "/ClientOrder/Cancel";
    static String addReview = mainUrl + "/CarWashReview/Add";
    static String updateClient = mainUrl + "/Client/UpdateInfo";

    public static GsonRequest<CancelOrderPojo> addReview(Response.Listener<CancelOrderPojo> responseHandler, Response.ErrorListener errorListener, final Map<String, String> params) {

        GsonRequest<CancelOrderPojo> jsonObjReq = new GsonRequest<CancelOrderPojo>(Request.Method.POST, addReview, CancelOrderPojo.class,
                null, null,
                responseHandler, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return params;
            }
        };
        return jsonObjReq;
    }

    public static GsonRequest<CancelOrderPojo> cancelOrder(Response.Listener<CancelOrderPojo> responseHandler, Response.ErrorListener errorListener, final Map<String, String> params) {

        GsonRequest<CancelOrderPojo> jsonObjReq = new GsonRequest<CancelOrderPojo>(Request.Method.POST, cancelOrder, CancelOrderPojo.class,
                null, null,
                responseHandler, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return params;
            }
        };
        return jsonObjReq;
    }

    public static GsonRequest<OrderPojo> orders(Response.Listener<OrderPojo> responseHandler, Response.ErrorListener errorListener, final Map<String, String> params) {

        GsonRequest<OrderPojo> jsonObjReq = new GsonRequest<OrderPojo>(Request.Method.POST, orders, OrderPojo.class,
                null, null,
                responseHandler, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return params;
            }
        };
        return jsonObjReq;
    }

    public static GsonRequest<ServicesPojo> services(Response.Listener<ServicesPojo> responseHandler, Response.ErrorListener errorListener, final Map<String, String> params) {

        GsonRequest<ServicesPojo> jsonObjReq = new GsonRequest<ServicesPojo>(Request.Method.POST, services, ServicesPojo.class,
                null, null,
                responseHandler, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return params;
            }
        };
        return jsonObjReq;
    }

    public static GsonRequest<SearchPojo> search(Response.Listener<SearchPojo> responseHandler, Response.ErrorListener errorListener, final Map<String, String> params) {

        GsonRequest<SearchPojo> jsonObjReq = new GsonRequest<SearchPojo>(Request.Method.POST, search, SearchPojo.class,
                null, null,
                responseHandler, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return params;
            }
        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                35000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        return jsonObjReq;
    }

    public static GsonRequest<AllCarwashPojo> getCarwashList(Response.Listener<AllCarwashPojo> responseHandler, Response.ErrorListener errorListener, final Map<String, String> params) {

        GsonRequest<AllCarwashPojo> jsonObjReq = new GsonRequest<AllCarwashPojo>(Request.Method.POST, getCarwashList, AllCarwashPojo.class,
                null, null,
                responseHandler, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return params;
            }
        };
        return jsonObjReq;
    }

    public static GsonRequest<CurrentCarwashPojo> getCurrentCarwash(Response.Listener<CurrentCarwashPojo> responseHandler, Response.ErrorListener errorListener, final Map<String, String> params) {

        GsonRequest<CurrentCarwashPojo> jsonObjReq = new GsonRequest<CurrentCarwashPojo>(Request.Method.POST, getCurrentCarwash, CurrentCarwashPojo.class,
                null, null,
                responseHandler, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return params;
            }
        };
        return jsonObjReq;
    }

    public static GsonRequest<CarwashFeedbacksPojo> getCarwashFeedbacks(Response.Listener<CarwashFeedbacksPojo> responseHandler, Response.ErrorListener errorListener, final Map<String, String> params) {

        GsonRequest<CarwashFeedbacksPojo> jsonObjReq = new GsonRequest<CarwashFeedbacksPojo>(Request.Method.POST, getCarwashFeedbacks, CarwashFeedbacksPojo.class,
                null, null,
                responseHandler, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return params;
            }
        };
        return jsonObjReq;
    }

    public static GsonRequest<AllCarMarkaPojo> getAllCarMarka(Response.Listener<AllCarMarkaPojo> responseHandler, Response.ErrorListener errorListener, final Map<String, String> params) {

        GsonRequest<AllCarMarkaPojo> jsonObjReq = new GsonRequest<AllCarMarkaPojo>(Request.Method.POST, getCarMarks, AllCarMarkaPojo.class,
                null, null,
                responseHandler, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return params;
            }
        };
        return jsonObjReq;
    }

    public static GsonRequest<CarModelsPojo> getAllCarModels(Response.Listener<CarModelsPojo> responseHandler, Response.ErrorListener errorListener, final Map<String, String> params) {

        GsonRequest<CarModelsPojo> jsonObjReq = new GsonRequest<CarModelsPojo>(Request.Method.POST, getCarModels, CarModelsPojo.class,
                null, null,
                responseHandler, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return params;
            }
        };
        return jsonObjReq;
    }

    public static GsonRequest<RegionsPojo> getRegions(Response.Listener<RegionsPojo> responseHandler, Response.ErrorListener errorListener, final Map<String, String> params) {

        GsonRequest<RegionsPojo> jsonObjReq = new GsonRequest<RegionsPojo>(Request.Method.POST, getRegions, RegionsPojo.class,
                null, null,
                responseHandler, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return params;
            }
        };
        return jsonObjReq;
    }

    public static GsonRequest<UserPojo> registration(Response.Listener<UserPojo> responseHandler, Response.ErrorListener errorListener, final Map<String, String> params) {

        GsonRequest<UserPojo> jsonObjReq = new GsonRequest<UserPojo>(Request.Method.POST, getRegistration, UserPojo.class,
                null, null,
                responseHandler, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return params;
            }
        };
        return jsonObjReq;
    }

    public static GsonRequest<CheckSMSPojo> checkSmsCode(Response.Listener<CheckSMSPojo> responseHandler, Response.ErrorListener errorListener, final Map<String, String> params) {

        GsonRequest<CheckSMSPojo> jsonObjReq = new GsonRequest<CheckSMSPojo>(Request.Method.POST, getSmsCode, CheckSMSPojo.class,
                null, null,
                responseHandler, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return params;
            }
        };
        return jsonObjReq;
    }

    public static GsonRequest<AddOrderPojo> addOrder(Response.Listener<AddOrderPojo> responseHandler, Response.ErrorListener errorListener, final Map<String, String> params) {

        GsonRequest<AddOrderPojo> jsonObjReq = new GsonRequest<AddOrderPojo>(Request.Method.POST, addOrder, AddOrderPojo.class,
                null, null,
                responseHandler, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return params;
            }
        };
        return jsonObjReq;
    }

    public static GsonRequest<LoginPojo> login(Response.Listener<LoginPojo> responseHandler, Response.ErrorListener errorListener, final Map<String, String> params) {

        GsonRequest<LoginPojo> jsonObjReq = new GsonRequest<LoginPojo>(Request.Method.POST, login, LoginPojo.class,
                null, null,
                responseHandler, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return params;
            }
        };
        return jsonObjReq;
    }

    public static GsonRequest<CurrentUserPojo> client(Response.Listener<CurrentUserPojo> responseHandler, Response.ErrorListener errorListener, final Map<String, String> params) {

        GsonRequest<CurrentUserPojo> jsonObjReq = new GsonRequest<CurrentUserPojo>(Request.Method.POST, client, CurrentUserPojo.class,
                null, null,
                responseHandler, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return params;
            }
        };
        return jsonObjReq;
    }
    public static GsonRequest<CurrentUserPojo> updateClient(Response.Listener<CurrentUserPojo> responseHandler, Response.ErrorListener errorListener, final Map<String, String> params) {

        GsonRequest<CurrentUserPojo> jsonObjReq = new GsonRequest<CurrentUserPojo>(Request.Method.POST, updateClient, CurrentUserPojo.class,
                null, null,
                responseHandler, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return params;
            }
        };
        return jsonObjReq;
    }

    public static GsonRequest<TimesPojo> getTimes(Response.Listener<TimesPojo> responseHandler, Response.ErrorListener errorListener, final Map<String, String> params) {

        GsonRequest<TimesPojo> jsonObjReq = new GsonRequest<TimesPojo>(Request.Method.POST, times, TimesPojo.class,
                null, null,
                responseHandler, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                return params;
            }
        };
        return jsonObjReq;
    }
}