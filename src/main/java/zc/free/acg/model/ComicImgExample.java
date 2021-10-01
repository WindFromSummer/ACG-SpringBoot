package zc.free.acg.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComicImgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ComicImgExample() {
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

        public Criteria andImgIsNull() {
            addCriterion("img is null");
            return (Criteria) this;
        }

        public Criteria andImgIsNotNull() {
            addCriterion("img is not null");
            return (Criteria) this;
        }

        public Criteria andImgEqualTo(String value) {
            addCriterion("img =", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotEqualTo(String value) {
            addCriterion("img <>", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThan(String value) {
            addCriterion("img >", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgGreaterThanOrEqualTo(String value) {
            addCriterion("img >=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThan(String value) {
            addCriterion("img <", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLessThanOrEqualTo(String value) {
            addCriterion("img <=", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgLike(String value) {
            addCriterion("img like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotLike(String value) {
            addCriterion("img not like", value, "img");
            return (Criteria) this;
        }

        public Criteria andImgIn(List<String> values) {
            addCriterion("img in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotIn(List<String> values) {
            addCriterion("img not in", values, "img");
            return (Criteria) this;
        }

        public Criteria andImgBetween(String value1, String value2) {
            addCriterion("img between", value1, value2, "img");
            return (Criteria) this;
        }

        public Criteria andImgNotBetween(String value1, String value2) {
            addCriterion("img not between", value1, value2, "img");
            return (Criteria) this;
        }

        public Criteria andComeIsNull() {
            addCriterion("come is null");
            return (Criteria) this;
        }

        public Criteria andComeIsNotNull() {
            addCriterion("come is not null");
            return (Criteria) this;
        }

        public Criteria andComeEqualTo(String value) {
            addCriterion("come =", value, "come");
            return (Criteria) this;
        }

        public Criteria andComeNotEqualTo(String value) {
            addCriterion("come <>", value, "come");
            return (Criteria) this;
        }

        public Criteria andComeGreaterThan(String value) {
            addCriterion("come >", value, "come");
            return (Criteria) this;
        }

        public Criteria andComeGreaterThanOrEqualTo(String value) {
            addCriterion("come >=", value, "come");
            return (Criteria) this;
        }

        public Criteria andComeLessThan(String value) {
            addCriterion("come <", value, "come");
            return (Criteria) this;
        }

        public Criteria andComeLessThanOrEqualTo(String value) {
            addCriterion("come <=", value, "come");
            return (Criteria) this;
        }

        public Criteria andComeLike(String value) {
            addCriterion("come like", value, "come");
            return (Criteria) this;
        }

        public Criteria andComeNotLike(String value) {
            addCriterion("come not like", value, "come");
            return (Criteria) this;
        }

        public Criteria andComeIn(List<String> values) {
            addCriterion("come in", values, "come");
            return (Criteria) this;
        }

        public Criteria andComeNotIn(List<String> values) {
            addCriterion("come not in", values, "come");
            return (Criteria) this;
        }

        public Criteria andComeBetween(String value1, String value2) {
            addCriterion("come between", value1, value2, "come");
            return (Criteria) this;
        }

        public Criteria andComeNotBetween(String value1, String value2) {
            addCriterion("come not between", value1, value2, "come");
            return (Criteria) this;
        }

        public Criteria andUploadTimeIsNull() {
            addCriterion("upload_time is null");
            return (Criteria) this;
        }

        public Criteria andUploadTimeIsNotNull() {
            addCriterion("upload_time is not null");
            return (Criteria) this;
        }

        public Criteria andUploadTimeEqualTo(Date value) {
            addCriterion("upload_time =", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotEqualTo(Date value) {
            addCriterion("upload_time <>", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeGreaterThan(Date value) {
            addCriterion("upload_time >", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("upload_time >=", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeLessThan(Date value) {
            addCriterion("upload_time <", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeLessThanOrEqualTo(Date value) {
            addCriterion("upload_time <=", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeIn(List<Date> values) {
            addCriterion("upload_time in", values, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotIn(List<Date> values) {
            addCriterion("upload_time not in", values, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeBetween(Date value1, Date value2) {
            addCriterion("upload_time between", value1, value2, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotBetween(Date value1, Date value2) {
            addCriterion("upload_time not between", value1, value2, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadUserIsNull() {
            addCriterion("upload_user is null");
            return (Criteria) this;
        }

        public Criteria andUploadUserIsNotNull() {
            addCriterion("upload_user is not null");
            return (Criteria) this;
        }

        public Criteria andUploadUserEqualTo(Integer value) {
            addCriterion("upload_user =", value, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserNotEqualTo(Integer value) {
            addCriterion("upload_user <>", value, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserGreaterThan(Integer value) {
            addCriterion("upload_user >", value, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserGreaterThanOrEqualTo(Integer value) {
            addCriterion("upload_user >=", value, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserLessThan(Integer value) {
            addCriterion("upload_user <", value, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserLessThanOrEqualTo(Integer value) {
            addCriterion("upload_user <=", value, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserIn(List<Integer> values) {
            addCriterion("upload_user in", values, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserNotIn(List<Integer> values) {
            addCriterion("upload_user not in", values, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserBetween(Integer value1, Integer value2) {
            addCriterion("upload_user between", value1, value2, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserNotBetween(Integer value1, Integer value2) {
            addCriterion("upload_user not between", value1, value2, "uploadUser");
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