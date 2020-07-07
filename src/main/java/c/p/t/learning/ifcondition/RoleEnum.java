package c.p.t.learning.ifcondition;

public enum  RoleEnum implements RoleInterface {

    ADMIN{
        @Override
        public String op() {
            return "admin permission";
        }
    },

    SIMPLE{
        @Override
        public String op() {
            return "simple permission";
        }
    }

}
