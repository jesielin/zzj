package cc.shawn.zzj.network;

import java.util.List;

import cc.shawn.zzj.bean.IndexAdvert;
import cc.shawn.zzj.bean.Notice;
import cc.shawn.zzj.bean.Tech;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by shawn on 2017-02-07.
 */

public interface HttpService {

    /**
     * 首页广告
     * @param position
     * @param sign
     * @return
     */
    @GET("/getIndexAdvert")
    Observable<HttpResult<List<IndexAdvert>>> getIndexAdvert(@Query("position") int position,@Query("sign") String sign);

    /**
     * 登录
     * @param loginName
     * @param identifyingCode
     * @param sign
     * @return
     */
    @GET("/login")
    Observable<HttpResult<Object>> login(@Query("loginName")String loginName,@Query("identifyingCode") String identifyingCode,@Query("sign") String sign);

    /**
     * 注册
     * @param loginName
     * @param identifyingCode
     * @param regType 1 技师 0 用户
     * @param sign
     * @return
     */
    @GET("/register")
    Observable<HttpResult<Object>> register(@Query("loginName")String loginName,@Query("identifyingCode")String identifyingCode,@Query("regType")String regType,@Query("sign") String sign);

    /**
     * 首页动态
     * @param currentDate 当前日期 2017-02-21
     * @param sign
     * @return
     */
    Observable<HttpResult<List<Notice>>> getNotice(@Query("currentDate")String currentDate,@Query("sign") String sign);

    /**
     * 首页推荐技师
     * @param size 推荐人数控制
     * @param sign
     * @return
     */
    Observable<HttpResult<List<Tech>>> getRecommendTechs(@Query("size")int size,@Query("sign") String sign);

    /**
     * 首页搜索技师
     * @param currentPage 当前页码 初始1
     * @param size 查询人数控制
     * @param techName 待搜索的技师昵称名称（模糊搜索）
     * @param sign
     * @return
     */
    Observable<HttpResult<List<Tech>>> searchTechs(@Query("currentPage")int currentPage,@Query("size")int size,@Query("techName")String techName,@Query("sign") String sign);

    /**
     * 更新用户信息
     * @param uuid 用户唯一标示
     * @param nickName 用户昵称
     * @param status 用户状态  0未启用 1启用
     * @param userType 用户类型 1技师 2普通用户
     * @param level 用户等级
     * @param isRecommend 是否推荐技师  1推荐
     * @param summary 简介
     * @param headSculpture 头像
     * @param sex 性别  1男 0女
     * @return
     */
    @GET("/setUserinfo")
    Observable<HttpResult<Object>> setUserinfo(
            @Query("uuid")String uuid,
            @Query("nickName")String nickName,
            @Query("status")String status,
            @Query("userType")int userType,
            @Query("level")String level,
            @Query("isRecommend")int isRecommend,
            @Query("summary")String summary,
            @Query("headSculpture")String headSculpture,
            @Query("sex")String sex,
            @Query("sign") String sign
    );
}
