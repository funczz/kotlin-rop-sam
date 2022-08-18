package com.github.funczz.kotlin.rop_sam

import com.github.funczz.kotlin.rop.result.RopResult
import com.github.funczz.kotlin.rop.result.match

/**
 * SAM State Next Action インターフェイス:
 * SAM NextAction を定義するクラス。
 * SAM モデルに適用する SAM アクションを管理する。
 * @param M ISamModel: SAM モデル
 * @author funczz
 */
interface ISamStateNextAction<M : ISamModel> {

    /**
     * default method:
     * SAM NextAction メソッド。
     * Terminate が返されるまで、繰り返し nextActionPredicate メソッドを実行する。
     * @param model ISamModel: SAM モデル
     * @return RopResult M - ISamModel: SAM モデル
     */
    fun nextAction(model: M): RopResult<M> {
        var result = RopResult.tee { model }
        var data: NextActionData<M> = NextActionData.Continue(model)
        while (data is NextActionData.Continue<M>) {
            nextActionPredicate(data).match(
                success = {
                    result = RopResult.success { it.model }
                    data = it
                },
                failure = {
                    return RopResult.failure(it)
                },
            )
        }
        return result
    }

    /**
     * 渡されたデータクラスが Continue なら SAM Action を適用し、
     * Terminate ならそのまま返す。
     * @param data SamNextActionData: SAM Next Action データ
     * @return RopResult M - SamNextActionData: SAM Next Action データ。
     *         次に適用する SAM Action があるなら Continue を返し、
     *         ないなら Terminate を返す。
     */
    fun nextActionPredicate(data: NextActionData<M>): RopResult<NextActionData<M>>

}