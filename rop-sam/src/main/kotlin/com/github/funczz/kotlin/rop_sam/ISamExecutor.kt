package com.github.funczz.kotlin.rop_sam

import com.github.funczz.kotlin.rop.result.RopResult

/**
 * SAM Executor インターフェイス:
 * SAM へ処理を投入するクラス
 * @param D Any: SAM Action 入力データ
 * @param M ISamModel: SAM モデル
 * @param O representation 関数の実行結果
 * @author funczz
 */
interface ISamExecutor<D, M : ISamModel, R, O : Any> {

    /**
     * SAM Present 関数を返す
     * 通常は ISamModelPresent クラスに定義したメソッドを返す
     * @return SAM Present 関数
     */
    fun samPresent(): (D) -> RopResult<M>

    /**
     * SAM NextAction 関数を返す
     * 通常は ISamStateNextAction クラスに定義したメソッドを返す
     * @return SAM NextAction 関数
     */
    fun samNextAction(): (M) -> RopResult<M>

    /**
     * SAM Representation 関数を返す
     * 通常は ISamStateRepresentation クラスに定義したメソッドを返す
     * @return SAM Representation 関数
     */
    fun samRepresentation(): (M, R) -> RopResult<O>

    /**
     * default method:
     * SAM Action と SAM NextAction を適用した結果を返す。
     * @param samAction SAM Action
     * @param samActionData SAM Action 入力データ
     * @return Result M - ISamModel: SAM モデル
     */
    fun doAction(samAction: ISamAction<D, M>, samActionData: D): RopResult<M> {
        return samAction.execute(samPresent(), samActionData)
            .andThen { samNextAction()(it) }
    }

    /**
     * default method:
     * SAM Representation 関数を適用する。
     * @param model ISamModel: SAM モデル
     * @param representationData representation 処理で用いる値
     * @return Result doRepresentation の実行結果
     */
    fun doRepresentation(model: M, representationData: R): RopResult<O> {
        return samRepresentation()(model, representationData)
    }

    /**
     * default method:
     * doAction と doRepresentation を順に適用する。
     * @param samAction SAM Action
     * @param samActionData SAM Action 入力データ
     * @param representationData representation 処理で用いる値
     * @return Result doAction もしくは doRepresentation の実行結果
     */
    fun execute(samAction: ISamAction<D, M>, samActionData: D, representationData: R): RopResult<O> {
        return doAction(samAction, samActionData)
            .andThen { doRepresentation(it, representationData) }
    }
}