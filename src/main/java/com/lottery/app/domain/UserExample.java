package com.lottery.app.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserExample implements Serializable{
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andUseridIsNull() {
            addCriterion("userid is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userid is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(Integer value) {
            addCriterion("userid =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(Integer value) {
            addCriterion("userid <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(Integer value) {
            addCriterion("userid >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Integer value) {
            addCriterion("userid >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(Integer value) {
            addCriterion("userid <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(Integer value) {
            addCriterion("userid <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<Integer> values) {
            addCriterion("userid in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<Integer> values) {
            addCriterion("userid not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(Integer value1, Integer value2) {
            addCriterion("userid between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(Integer value1, Integer value2) {
            addCriterion("userid not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsertypeIsNull() {
            addCriterion("usertype is null");
            return (Criteria) this;
        }

        public Criteria andUsertypeIsNotNull() {
            addCriterion("usertype is not null");
            return (Criteria) this;
        }

        public Criteria andUsertypeEqualTo(Integer value) {
            addCriterion("usertype =", value, "usertype");
            return (Criteria) this;
        }

        public Criteria andUsertypeNotEqualTo(Integer value) {
            addCriterion("usertype <>", value, "usertype");
            return (Criteria) this;
        }

        public Criteria andUsertypeGreaterThan(Integer value) {
            addCriterion("usertype >", value, "usertype");
            return (Criteria) this;
        }

        public Criteria andUsertypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("usertype >=", value, "usertype");
            return (Criteria) this;
        }

        public Criteria andUsertypeLessThan(Integer value) {
            addCriterion("usertype <", value, "usertype");
            return (Criteria) this;
        }

        public Criteria andUsertypeLessThanOrEqualTo(Integer value) {
            addCriterion("usertype <=", value, "usertype");
            return (Criteria) this;
        }

        public Criteria andUsertypeIn(List<Integer> values) {
            addCriterion("usertype in", values, "usertype");
            return (Criteria) this;
        }

        public Criteria andUsertypeNotIn(List<Integer> values) {
            addCriterion("usertype not in", values, "usertype");
            return (Criteria) this;
        }

        public Criteria andUsertypeBetween(Integer value1, Integer value2) {
            addCriterion("usertype between", value1, value2, "usertype");
            return (Criteria) this;
        }

        public Criteria andUsertypeNotBetween(Integer value1, Integer value2) {
            addCriterion("usertype not between", value1, value2, "usertype");
            return (Criteria) this;
        }

        public Criteria andRecommenderIsNull() {
            addCriterion("recommender is null");
            return (Criteria) this;
        }

        public Criteria andRecommenderIsNotNull() {
            addCriterion("recommender is not null");
            return (Criteria) this;
        }

        public Criteria andRecommenderEqualTo(Integer value) {
            addCriterion("recommender =", value, "recommender");
            return (Criteria) this;
        }

        public Criteria andRecommenderNotEqualTo(Integer value) {
            addCriterion("recommender <>", value, "recommender");
            return (Criteria) this;
        }

        public Criteria andRecommenderGreaterThan(Integer value) {
            addCriterion("recommender >", value, "recommender");
            return (Criteria) this;
        }

        public Criteria andRecommenderGreaterThanOrEqualTo(Integer value) {
            addCriterion("recommender >=", value, "recommender");
            return (Criteria) this;
        }

        public Criteria andRecommenderLessThan(Integer value) {
            addCriterion("recommender <", value, "recommender");
            return (Criteria) this;
        }

        public Criteria andRecommenderLessThanOrEqualTo(Integer value) {
            addCriterion("recommender <=", value, "recommender");
            return (Criteria) this;
        }

        public Criteria andRecommenderIn(List<Integer> values) {
            addCriterion("recommender in", values, "recommender");
            return (Criteria) this;
        }

        public Criteria andRecommenderNotIn(List<Integer> values) {
            addCriterion("recommender not in", values, "recommender");
            return (Criteria) this;
        }

        public Criteria andRecommenderBetween(Integer value1, Integer value2) {
            addCriterion("recommender between", value1, value2, "recommender");
            return (Criteria) this;
        }

        public Criteria andRecommenderNotBetween(Integer value1, Integer value2) {
            addCriterion("recommender not between", value1, value2, "recommender");
            return (Criteria) this;
        }

        public Criteria andOrganizeIdIsNull() {
            addCriterion("organize_id is null");
            return (Criteria) this;
        }

        public Criteria andOrganizeIdIsNotNull() {
            addCriterion("organize_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrganizeIdEqualTo(String value) {
            addCriterion("organize_id =", value, "organizeId");
            return (Criteria) this;
        }

        public Criteria andOrganizeIdNotEqualTo(String value) {
            addCriterion("organize_id <>", value, "organizeId");
            return (Criteria) this;
        }

        public Criteria andOrganizeIdGreaterThan(String value) {
            addCriterion("organize_id >", value, "organizeId");
            return (Criteria) this;
        }

        public Criteria andOrganizeIdGreaterThanOrEqualTo(String value) {
            addCriterion("organize_id >=", value, "organizeId");
            return (Criteria) this;
        }

        public Criteria andOrganizeIdLessThan(String value) {
            addCriterion("organize_id <", value, "organizeId");
            return (Criteria) this;
        }

        public Criteria andOrganizeIdLessThanOrEqualTo(String value) {
            addCriterion("organize_id <=", value, "organizeId");
            return (Criteria) this;
        }

        public Criteria andOrganizeIdLike(String value) {
            addCriterion("organize_id like", value, "organizeId");
            return (Criteria) this;
        }

        public Criteria andOrganizeIdNotLike(String value) {
            addCriterion("organize_id not like", value, "organizeId");
            return (Criteria) this;
        }

        public Criteria andOrganizeIdIn(List<String> values) {
            addCriterion("organize_id in", values, "organizeId");
            return (Criteria) this;
        }

        public Criteria andOrganizeIdNotIn(List<String> values) {
            addCriterion("organize_id not in", values, "organizeId");
            return (Criteria) this;
        }

        public Criteria andOrganizeIdBetween(String value1, String value2) {
            addCriterion("organize_id between", value1, value2, "organizeId");
            return (Criteria) this;
        }

        public Criteria andOrganizeIdNotBetween(String value1, String value2) {
            addCriterion("organize_id not between", value1, value2, "organizeId");
            return (Criteria) this;
        }

        public Criteria andModifyDateIsNull() {
            addCriterion("modify_date is null");
            return (Criteria) this;
        }

        public Criteria andModifyDateIsNotNull() {
            addCriterion("modify_date is not null");
            return (Criteria) this;
        }

        public Criteria andModifyDateEqualTo(Date value) {
            addCriterion("modify_date =", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateNotEqualTo(Date value) {
            addCriterion("modify_date <>", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateGreaterThan(Date value) {
            addCriterion("modify_date >", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_date >=", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateLessThan(Date value) {
            addCriterion("modify_date <", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateLessThanOrEqualTo(Date value) {
            addCriterion("modify_date <=", value, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateIn(List<Date> values) {
            addCriterion("modify_date in", values, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateNotIn(List<Date> values) {
            addCriterion("modify_date not in", values, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateBetween(Date value1, Date value2) {
            addCriterion("modify_date between", value1, value2, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andModifyDateNotBetween(Date value1, Date value2) {
            addCriterion("modify_date not between", value1, value2, "modifyDate");
            return (Criteria) this;
        }

        public Criteria andHeadimageUrlIsNull() {
            addCriterion("headimage_url is null");
            return (Criteria) this;
        }

        public Criteria andHeadimageUrlIsNotNull() {
            addCriterion("headimage_url is not null");
            return (Criteria) this;
        }

        public Criteria andHeadimageUrlEqualTo(String value) {
            addCriterion("headimage_url =", value, "headimageUrl");
            return (Criteria) this;
        }

        public Criteria andHeadimageUrlNotEqualTo(String value) {
            addCriterion("headimage_url <>", value, "headimageUrl");
            return (Criteria) this;
        }

        public Criteria andHeadimageUrlGreaterThan(String value) {
            addCriterion("headimage_url >", value, "headimageUrl");
            return (Criteria) this;
        }

        public Criteria andHeadimageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("headimage_url >=", value, "headimageUrl");
            return (Criteria) this;
        }

        public Criteria andHeadimageUrlLessThan(String value) {
            addCriterion("headimage_url <", value, "headimageUrl");
            return (Criteria) this;
        }

        public Criteria andHeadimageUrlLessThanOrEqualTo(String value) {
            addCriterion("headimage_url <=", value, "headimageUrl");
            return (Criteria) this;
        }

        public Criteria andHeadimageUrlLike(String value) {
            addCriterion("headimage_url like", value, "headimageUrl");
            return (Criteria) this;
        }

        public Criteria andHeadimageUrlNotLike(String value) {
            addCriterion("headimage_url not like", value, "headimageUrl");
            return (Criteria) this;
        }

        public Criteria andHeadimageUrlIn(List<String> values) {
            addCriterion("headimage_url in", values, "headimageUrl");
            return (Criteria) this;
        }

        public Criteria andHeadimageUrlNotIn(List<String> values) {
            addCriterion("headimage_url not in", values, "headimageUrl");
            return (Criteria) this;
        }

        public Criteria andHeadimageUrlBetween(String value1, String value2) {
            addCriterion("headimage_url between", value1, value2, "headimageUrl");
            return (Criteria) this;
        }

        public Criteria andHeadimageUrlNotBetween(String value1, String value2) {
            addCriterion("headimage_url not between", value1, value2, "headimageUrl");
            return (Criteria) this;
        }

        public Criteria andPhoneNumIsNull() {
            addCriterion("phone_num is null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumIsNotNull() {
            addCriterion("phone_num is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumEqualTo(String value) {
            addCriterion("phone_num =", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotEqualTo(String value) {
            addCriterion("phone_num <>", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumGreaterThan(String value) {
            addCriterion("phone_num >", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumGreaterThanOrEqualTo(String value) {
            addCriterion("phone_num >=", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumLessThan(String value) {
            addCriterion("phone_num <", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumLessThanOrEqualTo(String value) {
            addCriterion("phone_num <=", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumLike(String value) {
            addCriterion("phone_num like", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotLike(String value) {
            addCriterion("phone_num not like", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumIn(List<String> values) {
            addCriterion("phone_num in", values, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotIn(List<String> values) {
            addCriterion("phone_num not in", values, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumBetween(String value1, String value2) {
            addCriterion("phone_num between", value1, value2, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotBetween(String value1, String value2) {
            addCriterion("phone_num not between", value1, value2, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andRegistDateIsNull() {
            addCriterion("regist_date is null");
            return (Criteria) this;
        }

        public Criteria andRegistDateIsNotNull() {
            addCriterion("regist_date is not null");
            return (Criteria) this;
        }

        public Criteria andRegistDateEqualTo(Date value) {
            addCriterion("regist_date =", value, "registDate");
            return (Criteria) this;
        }

        public Criteria andRegistDateNotEqualTo(Date value) {
            addCriterion("regist_date <>", value, "registDate");
            return (Criteria) this;
        }

        public Criteria andRegistDateGreaterThan(Date value) {
            addCriterion("regist_date >", value, "registDate");
            return (Criteria) this;
        }

        public Criteria andRegistDateGreaterThanOrEqualTo(Date value) {
            addCriterion("regist_date >=", value, "registDate");
            return (Criteria) this;
        }

        public Criteria andRegistDateLessThan(Date value) {
            addCriterion("regist_date <", value, "registDate");
            return (Criteria) this;
        }

        public Criteria andRegistDateLessThanOrEqualTo(Date value) {
            addCriterion("regist_date <=", value, "registDate");
            return (Criteria) this;
        }

        public Criteria andRegistDateIn(List<Date> values) {
            addCriterion("regist_date in", values, "registDate");
            return (Criteria) this;
        }

        public Criteria andRegistDateNotIn(List<Date> values) {
            addCriterion("regist_date not in", values, "registDate");
            return (Criteria) this;
        }

        public Criteria andRegistDateBetween(Date value1, Date value2) {
            addCriterion("regist_date between", value1, value2, "registDate");
            return (Criteria) this;
        }

        public Criteria andRegistDateNotBetween(Date value1, Date value2) {
            addCriterion("regist_date not between", value1, value2, "registDate");
            return (Criteria) this;
        }

        public Criteria andValidTagIsNull() {
            addCriterion("valid_tag is null");
            return (Criteria) this;
        }

        public Criteria andValidTagIsNotNull() {
            addCriterion("valid_tag is not null");
            return (Criteria) this;
        }

        public Criteria andValidTagEqualTo(String value) {
            addCriterion("valid_tag =", value, "validTag");
            return (Criteria) this;
        }

        public Criteria andValidTagNotEqualTo(String value) {
            addCriterion("valid_tag <>", value, "validTag");
            return (Criteria) this;
        }

        public Criteria andValidTagGreaterThan(String value) {
            addCriterion("valid_tag >", value, "validTag");
            return (Criteria) this;
        }

        public Criteria andValidTagGreaterThanOrEqualTo(String value) {
            addCriterion("valid_tag >=", value, "validTag");
            return (Criteria) this;
        }

        public Criteria andValidTagLessThan(String value) {
            addCriterion("valid_tag <", value, "validTag");
            return (Criteria) this;
        }

        public Criteria andValidTagLessThanOrEqualTo(String value) {
            addCriterion("valid_tag <=", value, "validTag");
            return (Criteria) this;
        }

        public Criteria andValidTagLike(String value) {
            addCriterion("valid_tag like", value, "validTag");
            return (Criteria) this;
        }

        public Criteria andValidTagNotLike(String value) {
            addCriterion("valid_tag not like", value, "validTag");
            return (Criteria) this;
        }

        public Criteria andValidTagIn(List<String> values) {
            addCriterion("valid_tag in", values, "validTag");
            return (Criteria) this;
        }

        public Criteria andValidTagNotIn(List<String> values) {
            addCriterion("valid_tag not in", values, "validTag");
            return (Criteria) this;
        }

        public Criteria andValidTagBetween(String value1, String value2) {
            addCriterion("valid_tag between", value1, value2, "validTag");
            return (Criteria) this;
        }

        public Criteria andValidTagNotBetween(String value1, String value2) {
            addCriterion("valid_tag not between", value1, value2, "validTag");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}