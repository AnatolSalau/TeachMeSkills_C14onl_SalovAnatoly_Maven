package by.salov.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) //Где аннотация будет использоваться (компиляция, runtime)
@Target(ElementType.METHOD) //аннотацию можно ставить над методами
public @interface BenchMark {

}
