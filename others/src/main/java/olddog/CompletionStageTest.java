package olddog;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * CompletionStageTest
 *
 * @author yong.han
 * 2019/2/26
 */
public interface CompletionStageTest<T> {

    /**
     * 本阶段正常完成时，返回一个新的 completionStage 来执行 fn, fn 消费本阶段的完成结果, 并生成一个新的返回值
     */
    public <U> CompletionStage<U> thenApply(Function<? super T, ? extends U> fn);
    public <U> CompletionStage<U> thenApplyAsync(Function<? super T, ? extends U> fn);
    public <U> CompletionStage<U> thenApplyAsync(Function<? super T, ? extends U> fn, Executor executor);

    /**
     * 本阶段正常完成时， 返回一个新的 CompletionStage 来执行 action,  action 消费本阶段结果， 没有返回值
     */
    public CompletionStage<Void> thenAccept(Consumer<? super T> action);
    public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action);
    public CompletionStage<Void> thenAcceptAsync(Consumer<? super T> action, Executor executor);

    /**
     * 本阶段正常完成时， 返回一个新的 CompletionStage 来执行 action,  action 没有参数，没有返回值
     */
    public CompletionStage<Void> thenRun(Runnable action);
    public CompletionStage<Void> thenRunAsync(Runnable action);
    public CompletionStage<Void> thenRunAsync(Runnable action, Executor executor);

    /**
     * 本阶段和 other 正常完成时， 返回一个新的 CompletionStage 来执行fn, fn 消费两个 stage 的结果， 并生成一个返回值
     */
    public <U,V> CompletionStage<V> thenCombine(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn);
    public <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn);
    public <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn, Executor executor);

    /**
     * 本阶段和 other 正常完成时， 返回一个新的 CompletionStage 来执行 action, action 消费两个 stage 的结果， 没有返回值
     */
    public <U> CompletionStage<Void> thenAcceptBoth(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action);
    public <U> CompletionStage<Void> thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action);
    public <U> CompletionStage<Void> thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action, Executor executor);

    /**
     * 本阶段和 other 正常完成时， 返回一个新的 CompletionStage 来执行 action, action 无参数和返回值
     */public CompletionStage<Void> runAfterBoth(CompletionStage<?> other, Runnable action);
    public CompletionStage<Void> runAfterBothAsync(CompletionStage<?> other, Runnable action);
    public CompletionStage<Void> runAfterBothAsync(CompletionStage<?> other, Runnable action, Executor executor);


    /**
     * 本阶段或 other 正常完成时，返回一个新的 CompletionStage 来执行 fn, fn 消费对应的完成结果， 并生成一个返回值
     */
    public <U> CompletionStage<U> applyToEither(CompletionStage<? extends T> other, Function<? super T, U> fn);
    public <U> CompletionStage<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T, U> fn);
    public <U> CompletionStage<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T, U> fn, Executor executor);

    /**
     *  本阶段或 other 正常完成时，返回一个新的 CompletionStage 来执行 action, action 消费对应的完成结果， 没有返回值
     */
    public CompletionStage<Void> acceptEither(CompletionStage<? extends T> other, Consumer<? super T> action);
    public CompletionStage<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action);
    public CompletionStage<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action, Executor executor);

    /**
     * 本阶段或 other 正常完成时，返回一个新的 CompletionStage 来执行 action, action 无参数和返回值
     */
    public CompletionStage<Void> runAfterEither(CompletionStage<?> other, Runnable action);
    public CompletionStage<Void> runAfterEitherAsync(CompletionStage<?> other, Runnable action);
    public CompletionStage<Void> runAfterEitherAsync(CompletionStage<?> other, Runnable action, Executor executor);

    /**
     * 与 thenApply 类似
     *
     * 与 thenApply 的区别？   有点类似于 map 与 flatMap 的区别~
     * https://stackoverflow.com/questions/43019126/completablefuture-thenapply-vs-thencompose
     *
     */
    public <U> CompletionStage<U> thenCompose(Function<? super T, ? extends CompletionStage<U>> fn);
    public <U> CompletionStage<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn);
    public <U> CompletionStage<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn, Executor executor);

    /**
     * 返回一个新的 CompletionStage:
     * 本 stage 异常时： 执行 fn 来消费 exception, 并返回一个值（可用来实现默认值~）
     * 本 stage 正常完成时：新CompletionStage 与 本 statge 状态、值一样
     */
    public CompletionStage<T> exceptionally(Function<Throwable, ? extends T> fn);

    /**
     * 返回一个新的 CompletionStage 和 本 stage 拥有一样的 result 或 exception
     *
     * action, 无论本 stage 正常完成还是异常， 都会以（result, exception）为参数执行， 没有返回值（下个stage 的状态和本stage 一样）
     *
     */
    public CompletionStage<T> whenComplete(BiConsumer<? super T, ? super Throwable> action);
    public CompletionStage<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action);
    public CompletionStage<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action, Executor executor);

    /**
     *  action, 无论本 stage 正常完成还是异常， 都会以（result, exception）为参数执行，
     *  action 的返回值做为下个stage 的结果值
     */
    public <U> CompletionStage<U> handle(BiFunction<? super T, Throwable, ? extends U> fn);
    public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn);
    public <U> CompletionStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn, Executor executor);

    /**
     *
     */
    public CompletableFuture<T> toCompletableFuture();

}
