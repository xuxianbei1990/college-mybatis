package college.model;

import java.io.Serializable;
import java.util.Date;

public class OrderBuyer implements Serializable {
    /**
     * 商品订单表主键id
     */
    private Long forderBuyerId;

    /**
     * 订单编号
     */
    private String forderNo;

    /**
     * 店铺id
     */
    private Integer fsellerId;

    /**
     * 商家名称
     */
    private String fshopName;

    /**
     * 用户id
     */
    private Integer fbuyerId;

    /**
     * 用户名称
     */
    private String fbuyerName;

    /**
     * 支付订单号（系统）
     */
    private Long forderPaymentId;

    /**
     * 商品金额(rmb)
     */
    private Long fgoodsCnAmount;

    /**
     * 商品税费(rmb)
     */
    private Long fgoodsTaxCnAmount;

    /**
     * 运费（rmb）
     */
    private Long ffreightCnAmount;

    /**
     * 优惠金额（rmb）
     */
    private Long fdiscountCnAmount;

    /**
     * 订单金额（rmb）
     */
    private Long forderCnAmount;

    /**
     * 币种
     */
    private String fcurrencyNameEn;

    /**
     * 汇率:(10000外币兑人民币)
     */
    private Integer fexchangeRate;

    /**
     * 订单类型（备用 默认：0）
     */
    private Byte forderType;

    /**
     * 订单状态,0：待支付，1：已支付（待发货），2：订单取消，3：已发货，4：已完成（签收），5：延迟收货 6：交易关闭 7: 待确认 8: 待提货
     */
    private Byte forderStatus;

    /**
     * 订单退款退货状态，0：未申请退货退款，1：申请退款退货，2：已退货退款，3：申请退货退款失败
     */
    private Byte fafterSaleStatus;

    /**
     * 订单推送状态，0：未推送，1：已推送,2:推送失败
     */
    private Byte fpushStatus;

    /**
     * 订单完成时间
     */
    private Date ffinishTime;

    /**
     * 订单推送时间
     */
    private Date fpushTime;

    /**
     * 订单发货时间
     */
    private Date fdeliveryTime;

    /**
     * 订单物流单号
     */
    private String flogisticsOrder;

    /**
     * 运费模板id
     */
    private Integer fcarriageTempId;

    /**
     * 收货人姓名
     */
    private String freceiveName;

    /**
     * 用户收货省编码
     */
    private Integer freceiveProvince;

    /**
     * 用户收货市编码
     */
    private Integer freceiveCity;

    /**
     * 用户收货区编码
     */
    private Integer freceiveRegion;

    /**
     * 详细收货地址
     */
    private String freceiveAddr;

    /**
     * 收货人手机号码
     */
    private String freceiveMobile;

    /**
     * 提货地址标记：0：普通；1：机场
     */
    private Byte freceiveAddrFlag;

    /**
     * 收货人邮箱
     */
    private String freceiveEmail;

    /**
     * 订购人表主键id
     */
    private Integer fordererId;

    /**
     * 订购人姓名
     */
    private String fordererName;

    /**
     * 订购人身份证
     */
    private String fordererIdCard;

    /**
     * 提货时间
     */
    private Date ftakeDeliveryTime;

    /**
     * 离境航班
     */
    private String fleaveFlightNumber;

    /**
     * 离境时间
     */
    private Date fleaveTime;

    /**
     * 支付人姓名
     */
    private String fpayerName;

    /**
     * 支付人身份证
     */
    private String fpayerIdCard;

    /**
     * 支付时间
     */
    private Date fpayTime;

    /**
     * 订单备注
     */
    private String forderRemark;

    /**
     * 创建（下单）时间
     */
    private Date fcreateTime;

    /**
     * 修改时间
     */
    private Date fmodifyTime;

    /**
     * 运费计算规则id
     */
    private Integer ffreightRuleId;

    /**
     * 订单来源：默认0； 1：代表预约提货
     */
    private Byte forderOrigin;

    private static final long serialVersionUID = 1L;

    public Long getForderBuyerId() {
        return forderBuyerId;
    }

    public void setForderBuyerId(Long forderBuyerId) {
        this.forderBuyerId = forderBuyerId;
    }

    public String getForderNo() {
        return forderNo;
    }

    public void setForderNo(String forderNo) {
        this.forderNo = forderNo == null ? null : forderNo.trim();
    }

    public Integer getFsellerId() {
        return fsellerId;
    }

    public void setFsellerId(Integer fsellerId) {
        this.fsellerId = fsellerId;
    }

    public String getFshopName() {
        return fshopName;
    }

    public void setFshopName(String fshopName) {
        this.fshopName = fshopName == null ? null : fshopName.trim();
    }

    public Integer getFbuyerId() {
        return fbuyerId;
    }

    public void setFbuyerId(Integer fbuyerId) {
        this.fbuyerId = fbuyerId;
    }

    public String getFbuyerName() {
        return fbuyerName;
    }

    public void setFbuyerName(String fbuyerName) {
        this.fbuyerName = fbuyerName == null ? null : fbuyerName.trim();
    }

    public Long getForderPaymentId() {
        return forderPaymentId;
    }

    public void setForderPaymentId(Long forderPaymentId) {
        this.forderPaymentId = forderPaymentId;
    }

    public Long getFgoodsCnAmount() {
        return fgoodsCnAmount;
    }

    public void setFgoodsCnAmount(Long fgoodsCnAmount) {
        this.fgoodsCnAmount = fgoodsCnAmount;
    }

    public Long getFgoodsTaxCnAmount() {
        return fgoodsTaxCnAmount;
    }

    public void setFgoodsTaxCnAmount(Long fgoodsTaxCnAmount) {
        this.fgoodsTaxCnAmount = fgoodsTaxCnAmount;
    }

    public Long getFfreightCnAmount() {
        return ffreightCnAmount;
    }

    public void setFfreightCnAmount(Long ffreightCnAmount) {
        this.ffreightCnAmount = ffreightCnAmount;
    }

    public Long getFdiscountCnAmount() {
        return fdiscountCnAmount;
    }

    public void setFdiscountCnAmount(Long fdiscountCnAmount) {
        this.fdiscountCnAmount = fdiscountCnAmount;
    }

    public Long getForderCnAmount() {
        return forderCnAmount;
    }

    public void setForderCnAmount(Long forderCnAmount) {
        this.forderCnAmount = forderCnAmount;
    }

    public String getFcurrencyNameEn() {
        return fcurrencyNameEn;
    }

    public void setFcurrencyNameEn(String fcurrencyNameEn) {
        this.fcurrencyNameEn = fcurrencyNameEn == null ? null : fcurrencyNameEn.trim();
    }

    public Integer getFexchangeRate() {
        return fexchangeRate;
    }

    public void setFexchangeRate(Integer fexchangeRate) {
        this.fexchangeRate = fexchangeRate;
    }

    public Byte getForderType() {
        return forderType;
    }

    public void setForderType(Byte forderType) {
        this.forderType = forderType;
    }

    public Byte getForderStatus() {
        return forderStatus;
    }

    public void setForderStatus(Byte forderStatus) {
        this.forderStatus = forderStatus;
    }

    public Byte getFafterSaleStatus() {
        return fafterSaleStatus;
    }

    public void setFafterSaleStatus(Byte fafterSaleStatus) {
        this.fafterSaleStatus = fafterSaleStatus;
    }

    public Byte getFpushStatus() {
        return fpushStatus;
    }

    public void setFpushStatus(Byte fpushStatus) {
        this.fpushStatus = fpushStatus;
    }

    public Date getFfinishTime() {
        return ffinishTime;
    }

    public void setFfinishTime(Date ffinishTime) {
        this.ffinishTime = ffinishTime;
    }

    public Date getFpushTime() {
        return fpushTime;
    }

    public void setFpushTime(Date fpushTime) {
        this.fpushTime = fpushTime;
    }

    public Date getFdeliveryTime() {
        return fdeliveryTime;
    }

    public void setFdeliveryTime(Date fdeliveryTime) {
        this.fdeliveryTime = fdeliveryTime;
    }

    public String getFlogisticsOrder() {
        return flogisticsOrder;
    }

    public void setFlogisticsOrder(String flogisticsOrder) {
        this.flogisticsOrder = flogisticsOrder == null ? null : flogisticsOrder.trim();
    }

    public Integer getFcarriageTempId() {
        return fcarriageTempId;
    }

    public void setFcarriageTempId(Integer fcarriageTempId) {
        this.fcarriageTempId = fcarriageTempId;
    }

    public String getFreceiveName() {
        return freceiveName;
    }

    public void setFreceiveName(String freceiveName) {
        this.freceiveName = freceiveName == null ? null : freceiveName.trim();
    }

    public Integer getFreceiveProvince() {
        return freceiveProvince;
    }

    public void setFreceiveProvince(Integer freceiveProvince) {
        this.freceiveProvince = freceiveProvince;
    }

    public Integer getFreceiveCity() {
        return freceiveCity;
    }

    public void setFreceiveCity(Integer freceiveCity) {
        this.freceiveCity = freceiveCity;
    }

    public Integer getFreceiveRegion() {
        return freceiveRegion;
    }

    public void setFreceiveRegion(Integer freceiveRegion) {
        this.freceiveRegion = freceiveRegion;
    }

    public String getFreceiveAddr() {
        return freceiveAddr;
    }

    public void setFreceiveAddr(String freceiveAddr) {
        this.freceiveAddr = freceiveAddr == null ? null : freceiveAddr.trim();
    }

    public String getFreceiveMobile() {
        return freceiveMobile;
    }

    public void setFreceiveMobile(String freceiveMobile) {
        this.freceiveMobile = freceiveMobile == null ? null : freceiveMobile.trim();
    }

    public Byte getFreceiveAddrFlag() {
        return freceiveAddrFlag;
    }

    public void setFreceiveAddrFlag(Byte freceiveAddrFlag) {
        this.freceiveAddrFlag = freceiveAddrFlag;
    }

    public String getFreceiveEmail() {
        return freceiveEmail;
    }

    public void setFreceiveEmail(String freceiveEmail) {
        this.freceiveEmail = freceiveEmail == null ? null : freceiveEmail.trim();
    }

    public Integer getFordererId() {
        return fordererId;
    }

    public void setFordererId(Integer fordererId) {
        this.fordererId = fordererId;
    }

    public String getFordererName() {
        return fordererName;
    }

    public void setFordererName(String fordererName) {
        this.fordererName = fordererName == null ? null : fordererName.trim();
    }

    public String getFordererIdCard() {
        return fordererIdCard;
    }

    public void setFordererIdCard(String fordererIdCard) {
        this.fordererIdCard = fordererIdCard == null ? null : fordererIdCard.trim();
    }

    public Date getFtakeDeliveryTime() {
        return ftakeDeliveryTime;
    }

    public void setFtakeDeliveryTime(Date ftakeDeliveryTime) {
        this.ftakeDeliveryTime = ftakeDeliveryTime;
    }

    public String getFleaveFlightNumber() {
        return fleaveFlightNumber;
    }

    public void setFleaveFlightNumber(String fleaveFlightNumber) {
        this.fleaveFlightNumber = fleaveFlightNumber == null ? null : fleaveFlightNumber.trim();
    }

    public Date getFleaveTime() {
        return fleaveTime;
    }

    public void setFleaveTime(Date fleaveTime) {
        this.fleaveTime = fleaveTime;
    }

    public String getFpayerName() {
        return fpayerName;
    }

    public void setFpayerName(String fpayerName) {
        this.fpayerName = fpayerName == null ? null : fpayerName.trim();
    }

    public String getFpayerIdCard() {
        return fpayerIdCard;
    }

    public void setFpayerIdCard(String fpayerIdCard) {
        this.fpayerIdCard = fpayerIdCard == null ? null : fpayerIdCard.trim();
    }

    public Date getFpayTime() {
        return fpayTime;
    }

    public void setFpayTime(Date fpayTime) {
        this.fpayTime = fpayTime;
    }

    public String getForderRemark() {
        return forderRemark;
    }

    public void setForderRemark(String forderRemark) {
        this.forderRemark = forderRemark == null ? null : forderRemark.trim();
    }

    public Date getFcreateTime() {
        return fcreateTime;
    }

    public void setFcreateTime(Date fcreateTime) {
        this.fcreateTime = fcreateTime;
    }

    public Date getFmodifyTime() {
        return fmodifyTime;
    }

    public void setFmodifyTime(Date fmodifyTime) {
        this.fmodifyTime = fmodifyTime;
    }

    public Integer getFfreightRuleId() {
        return ffreightRuleId;
    }

    public void setFfreightRuleId(Integer ffreightRuleId) {
        this.ffreightRuleId = ffreightRuleId;
    }

    public Byte getForderOrigin() {
        return forderOrigin;
    }

    public void setForderOrigin(Byte forderOrigin) {
        this.forderOrigin = forderOrigin;
    }
}