package context;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class TestInvocationContext {
    private static final Gson GSON = new Gson();
    private static final int MAX_DISPLAY_NAME_LENGTH = 255;
    private final String thread = Thread.currentThread().getName();
    private String className;
    private String methodName;
    private String displayName;
    private List<String> parameters;
    private List<String> parameterClassNames;

    public String toString() {
        StringBuilder builderPattern = new StringBuilder("[%s]: %s.%s(%s)");
        List<Object> buildParameters = new ArrayList();
        buildParameters.add(Thread.currentThread().getName());
        buildParameters.add(this.className);
        buildParameters.add(this.methodName);
        buildParameters.add(String.join(", ", this.parameterClassNames));

        return String.format(builderPattern.toString(), buildParameters.toArray());
    }

    public String asJsonString() {
        return GSON.toJson(this);
    }

    public static TestInvocationContext fromJsonString(String jsonContext) {
        return (TestInvocationContext)GSON.fromJson(jsonContext, TestInvocationContext.class);
    }

    public static TestInvocationContext.TestInvocationContextBuilder builder() {
        return new TestInvocationContext.TestInvocationContextBuilder();
    }

    public String getThread() {
        return this.thread;
    }

    public String getClassName() {
        return this.className;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public List<String> getParameters() {
        return this.parameters;
    }

    public List<String> getParameterClassNames() {
        return this.parameterClassNames;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public void setParameterClassNames(List<String> parameterClassNames) {
        this.parameterClassNames = parameterClassNames;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof TestInvocationContext)) {
            return false;
        } else {
            TestInvocationContext other = (TestInvocationContext)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label95: {
                    Object this$thread = this.getThread();
                    Object other$thread = other.getThread();
                    if (this$thread == null) {
                        if (other$thread == null) {
                            break label95;
                        }
                    } else if (this$thread.equals(other$thread)) {
                        break label95;
                    }

                    return false;
                }

                Object this$className = this.getClassName();
                Object other$className = other.getClassName();
                if (this$className == null) {
                    if (other$className != null) {
                        return false;
                    }
                } else if (!this$className.equals(other$className)) {
                    return false;
                }

                Object this$methodName = this.getMethodName();
                Object other$methodName = other.getMethodName();
                if (this$methodName == null) {
                    if (other$methodName != null) {
                        return false;
                    }
                } else if (!this$methodName.equals(other$methodName)) {
                    return false;
                }

                label74: {
                    Object this$displayName = this.getDisplayName();
                    Object other$displayName = other.getDisplayName();
                    if (this$displayName == null) {
                        if (other$displayName == null) {
                            break label74;
                        }
                    } else if (this$displayName.equals(other$displayName)) {
                        break label74;
                    }

                    return false;
                }

                label67: {
                    Object this$parameters = this.getParameters();
                    Object other$parameters = other.getParameters();
                    if (this$parameters == null) {
                        if (other$parameters == null) {
                            break label67;
                        }
                    } else if (this$parameters.equals(other$parameters)) {
                        break label67;
                    }

                    return false;
                }

                Object this$parameterClassNames = this.getParameterClassNames();
                Object other$parameterClassNames = other.getParameterClassNames();
                if (this$parameterClassNames == null) {
                    if (other$parameterClassNames != null) {
                        return false;
                    }
                } else if (!this$parameterClassNames.equals(other$parameterClassNames)) {
                    return false;
                }


                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof TestInvocationContext;
    }

    public int hashCode() {
        int result = 1;
        Object $thread = this.getThread();
        result = result * 59 + ($thread == null ? 43 : $thread.hashCode());
        Object $className = this.getClassName();
        result = result * 59 + ($className == null ? 43 : $className.hashCode());
        Object $methodName = this.getMethodName();
        result = result * 59 + ($methodName == null ? 43 : $methodName.hashCode());
        Object $displayName = this.getDisplayName();
        result = result * 59 + ($displayName == null ? 43 : $displayName.hashCode());
        Object $parameters = this.getParameters();
        result = result * 59 + ($parameters == null ? 43 : $parameters.hashCode());
        Object $parameterClassNames = this.getParameterClassNames();
        result = result * 59 + ($parameterClassNames == null ? 43 : $parameterClassNames.hashCode());
        return result;
    }

    public TestInvocationContext() {
    }

    public TestInvocationContext(String className, String methodName, String displayName, List<String> parameters, List<String> parameterClassNames, int dataProviderIndex, int instanceIndex, int invocationIndex) {
        this.className = className;
        this.methodName = methodName;
        this.displayName = displayName;
        this.parameters = parameters;
        this.parameterClassNames = parameterClassNames;
    }

    public static class TestInvocationContextBuilder {
        private String className;
        private String methodName;
        private String displayName;
        private List<String> parameters;
        private List<String> parameterClassNames;
        private int dataProviderIndex;
        private int instanceIndex;
        private int invocationIndex;

        TestInvocationContextBuilder() {
        }

        public TestInvocationContext.TestInvocationContextBuilder className(String className) {
            this.className = className;
            return this;
        }

        public TestInvocationContext.TestInvocationContextBuilder methodName(String methodName) {
            this.methodName = methodName;
            return this;
        }

        public TestInvocationContext.TestInvocationContextBuilder displayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public TestInvocationContext.TestInvocationContextBuilder parameters(List<String> parameters) {
            this.parameters = parameters;
            return this;
        }

        public TestInvocationContext.TestInvocationContextBuilder parameterClassNames(List<String> parameterClassNames) {
            this.parameterClassNames = parameterClassNames;
            return this;
        }

        public TestInvocationContext.TestInvocationContextBuilder dataProviderIndex(int dataProviderIndex) {
            this.dataProviderIndex = dataProviderIndex;
            return this;
        }

        public TestInvocationContext.TestInvocationContextBuilder instanceIndex(int instanceIndex) {
            this.instanceIndex = instanceIndex;
            return this;
        }

        public TestInvocationContext.TestInvocationContextBuilder invocationIndex(int invocationIndex) {
            this.invocationIndex = invocationIndex;
            return this;
        }

        public TestInvocationContext build() {
            return new TestInvocationContext(this.className, this.methodName, this.displayName, this.parameters, this.parameterClassNames, this.dataProviderIndex, this.instanceIndex, this.invocationIndex);
        }

        public String toString() {
            return "TestInvocationContext.TestInvocationContextBuilder(className=" + this.className + ", methodName=" + this.methodName + ", displayName=" + this.displayName + ", parameters=" + this.parameters + ", parameterClassNames=" + this.parameterClassNames + ", dataProviderIndex=" + this.dataProviderIndex + ", instanceIndex=" + this.instanceIndex + ", invocationIndex=" + this.invocationIndex + ")";
        }
    }
}