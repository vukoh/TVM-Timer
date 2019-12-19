package seedu.address.logic;

/**
 * Enum that represents which mode the user is currently in.
 */
public enum FunctionMode {
    REGISTER {
        public String toString() {
            return "register";
        }
    },

    START {
        public String toString() {
            return "start";
        }
    },

    FINISH {
        public String toString() {
            return "finish";
        }
    },

    CALCULATE {
        public String toString() {
            return "calculate";
        }
    },

    UNDEFINED {
        public String toString() {
            return "undefined";
        }
    };
}
