package zc.free.acg.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserIdentityApplyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserIdentityApplyExample() {
        oredCriteria = new ArrayList<>();
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
            criteria = new ArrayList<>();
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andIdentityIdIsNull() {
            addCriterion("identity_id is null");
            return (Criteria) this;
        }

        public Criteria andIdentityIdIsNotNull() {
            addCriterion("identity_id is not null");
            return (Criteria) this;
        }

        public Criteria andIdentityIdEqualTo(Integer value) {
            addCriterion("identity_id =", value, "identityId");
            return (Criteria) this;
        }

        public Criteria andIdentityIdNotEqualTo(Integer value) {
            addCriterion("identity_id <>", value, "identityId");
            return (Criteria) this;
        }

        public Criteria andIdentityIdGreaterThan(Integer value) {
            addCriterion("identity_id >", value, "identityId");
            return (Criteria) this;
        }

        public Criteria andIdentityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("identity_id >=", value, "identityId");
            return (Criteria) this;
        }

        public Criteria andIdentityIdLessThan(Integer value) {
            addCriterion("identity_id <", value, "identityId");
            return (Criteria) this;
        }

        public Criteria andIdentityIdLessThanOrEqualTo(Integer value) {
            addCriterion("identity_id <=", value, "identityId");
            return (Criteria) this;
        }

        public Criteria andIdentityIdIn(List<Integer> values) {
            addCriterion("identity_id in", values, "identityId");
            return (Criteria) this;
        }

        public Criteria andIdentityIdNotIn(List<Integer> values) {
            addCriterion("identity_id not in", values, "identityId");
            return (Criteria) this;
        }

        public Criteria andIdentityIdBetween(Integer value1, Integer value2) {
            addCriterion("identity_id between", value1, value2, "identityId");
            return (Criteria) this;
        }

        public Criteria andIdentityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("identity_id not between", value1, value2, "identityId");
            return (Criteria) this;
        }

        public Criteria andApplyAtIsNull() {
            addCriterion("apply_at is null");
            return (Criteria) this;
        }

        public Criteria andApplyAtIsNotNull() {
            addCriterion("apply_at is not null");
            return (Criteria) this;
        }

        public Criteria andApplyAtEqualTo(Date value) {
            addCriterion("apply_at =", value, "applyAt");
            return (Criteria) this;
        }

        public Criteria andApplyAtNotEqualTo(Date value) {
            addCriterion("apply_at <>", value, "applyAt");
            return (Criteria) this;
        }

        public Criteria andApplyAtGreaterThan(Date value) {
            addCriterion("apply_at >", value, "applyAt");
            return (Criteria) this;
        }

        public Criteria andApplyAtGreaterThanOrEqualTo(Date value) {
            addCriterion("apply_at >=", value, "applyAt");
            return (Criteria) this;
        }

        public Criteria andApplyAtLessThan(Date value) {
            addCriterion("apply_at <", value, "applyAt");
            return (Criteria) this;
        }

        public Criteria andApplyAtLessThanOrEqualTo(Date value) {
            addCriterion("apply_at <=", value, "applyAt");
            return (Criteria) this;
        }

        public Criteria andApplyAtIn(List<Date> values) {
            addCriterion("apply_at in", values, "applyAt");
            return (Criteria) this;
        }

        public Criteria andApplyAtNotIn(List<Date> values) {
            addCriterion("apply_at not in", values, "applyAt");
            return (Criteria) this;
        }

        public Criteria andApplyAtBetween(Date value1, Date value2) {
            addCriterion("apply_at between", value1, value2, "applyAt");
            return (Criteria) this;
        }

        public Criteria andApplyAtNotBetween(Date value1, Date value2) {
            addCriterion("apply_at not between", value1, value2, "applyAt");
            return (Criteria) this;
        }

        public Criteria andReplyNameIsNull() {
            addCriterion("reply_name is null");
            return (Criteria) this;
        }

        public Criteria andReplyNameIsNotNull() {
            addCriterion("reply_name is not null");
            return (Criteria) this;
        }

        public Criteria andReplyNameEqualTo(String value) {
            addCriterion("reply_name =", value, "replyName");
            return (Criteria) this;
        }

        public Criteria andReplyNameNotEqualTo(String value) {
            addCriterion("reply_name <>", value, "replyName");
            return (Criteria) this;
        }

        public Criteria andReplyNameGreaterThan(String value) {
            addCriterion("reply_name >", value, "replyName");
            return (Criteria) this;
        }

        public Criteria andReplyNameGreaterThanOrEqualTo(String value) {
            addCriterion("reply_name >=", value, "replyName");
            return (Criteria) this;
        }

        public Criteria andReplyNameLessThan(String value) {
            addCriterion("reply_name <", value, "replyName");
            return (Criteria) this;
        }

        public Criteria andReplyNameLessThanOrEqualTo(String value) {
            addCriterion("reply_name <=", value, "replyName");
            return (Criteria) this;
        }

        public Criteria andReplyNameLike(String value) {
            addCriterion("reply_name like", value, "replyName");
            return (Criteria) this;
        }

        public Criteria andReplyNameNotLike(String value) {
            addCriterion("reply_name not like", value, "replyName");
            return (Criteria) this;
        }

        public Criteria andReplyNameIn(List<String> values) {
            addCriterion("reply_name in", values, "replyName");
            return (Criteria) this;
        }

        public Criteria andReplyNameNotIn(List<String> values) {
            addCriterion("reply_name not in", values, "replyName");
            return (Criteria) this;
        }

        public Criteria andReplyNameBetween(String value1, String value2) {
            addCriterion("reply_name between", value1, value2, "replyName");
            return (Criteria) this;
        }

        public Criteria andReplyNameNotBetween(String value1, String value2) {
            addCriterion("reply_name not between", value1, value2, "replyName");
            return (Criteria) this;
        }

        public Criteria andReplyInfoIsNull() {
            addCriterion("reply_info is null");
            return (Criteria) this;
        }

        public Criteria andReplyInfoIsNotNull() {
            addCriterion("reply_info is not null");
            return (Criteria) this;
        }

        public Criteria andReplyInfoEqualTo(String value) {
            addCriterion("reply_info =", value, "replyInfo");
            return (Criteria) this;
        }

        public Criteria andReplyInfoNotEqualTo(String value) {
            addCriterion("reply_info <>", value, "replyInfo");
            return (Criteria) this;
        }

        public Criteria andReplyInfoGreaterThan(String value) {
            addCriterion("reply_info >", value, "replyInfo");
            return (Criteria) this;
        }

        public Criteria andReplyInfoGreaterThanOrEqualTo(String value) {
            addCriterion("reply_info >=", value, "replyInfo");
            return (Criteria) this;
        }

        public Criteria andReplyInfoLessThan(String value) {
            addCriterion("reply_info <", value, "replyInfo");
            return (Criteria) this;
        }

        public Criteria andReplyInfoLessThanOrEqualTo(String value) {
            addCriterion("reply_info <=", value, "replyInfo");
            return (Criteria) this;
        }

        public Criteria andReplyInfoLike(String value) {
            addCriterion("reply_info like", value, "replyInfo");
            return (Criteria) this;
        }

        public Criteria andReplyInfoNotLike(String value) {
            addCriterion("reply_info not like", value, "replyInfo");
            return (Criteria) this;
        }

        public Criteria andReplyInfoIn(List<String> values) {
            addCriterion("reply_info in", values, "replyInfo");
            return (Criteria) this;
        }

        public Criteria andReplyInfoNotIn(List<String> values) {
            addCriterion("reply_info not in", values, "replyInfo");
            return (Criteria) this;
        }

        public Criteria andReplyInfoBetween(String value1, String value2) {
            addCriterion("reply_info between", value1, value2, "replyInfo");
            return (Criteria) this;
        }

        public Criteria andReplyInfoNotBetween(String value1, String value2) {
            addCriterion("reply_info not between", value1, value2, "replyInfo");
            return (Criteria) this;
        }

        public Criteria andApplyStateIsNull() {
            addCriterion("apply_state is null");
            return (Criteria) this;
        }

        public Criteria andApplyStateIsNotNull() {
            addCriterion("apply_state is not null");
            return (Criteria) this;
        }

        public Criteria andApplyStateEqualTo(Byte value) {
            addCriterion("apply_state =", value, "applyState");
            return (Criteria) this;
        }

        public Criteria andApplyStateNotEqualTo(Byte value) {
            addCriterion("apply_state <>", value, "applyState");
            return (Criteria) this;
        }

        public Criteria andApplyStateGreaterThan(Byte value) {
            addCriterion("apply_state >", value, "applyState");
            return (Criteria) this;
        }

        public Criteria andApplyStateGreaterThanOrEqualTo(Byte value) {
            addCriterion("apply_state >=", value, "applyState");
            return (Criteria) this;
        }

        public Criteria andApplyStateLessThan(Byte value) {
            addCriterion("apply_state <", value, "applyState");
            return (Criteria) this;
        }

        public Criteria andApplyStateLessThanOrEqualTo(Byte value) {
            addCriterion("apply_state <=", value, "applyState");
            return (Criteria) this;
        }

        public Criteria andApplyStateIn(List<Byte> values) {
            addCriterion("apply_state in", values, "applyState");
            return (Criteria) this;
        }

        public Criteria andApplyStateNotIn(List<Byte> values) {
            addCriterion("apply_state not in", values, "applyState");
            return (Criteria) this;
        }

        public Criteria andApplyStateBetween(Byte value1, Byte value2) {
            addCriterion("apply_state between", value1, value2, "applyState");
            return (Criteria) this;
        }

        public Criteria andApplyStateNotBetween(Byte value1, Byte value2) {
            addCriterion("apply_state not between", value1, value2, "applyState");
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