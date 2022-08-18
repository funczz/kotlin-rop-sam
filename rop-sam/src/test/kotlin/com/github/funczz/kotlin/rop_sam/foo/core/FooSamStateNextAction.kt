package io.kotlintest.provided.com.github.funczz.kotlin.rop_sam.foo.core

import com.github.funczz.kotlin.rop.result.RopResult
import com.github.funczz.kotlin.rop_sam.ISamStateNextAction
import com.github.funczz.kotlin.rop_sam.NextActionData

object FooSamStateNextAction : IFooSamState, ISamStateNextAction<FooSamModel> {

    override fun nextActionPredicate(
        data: NextActionData<FooSamModel>
    ): RopResult<NextActionData<FooSamModel>> {
        return RopResult.tee {
            when (data) {
                is NextActionData.Terminate<FooSamModel> -> data
                is NextActionData.Continue<FooSamModel> -> {
                    when (isBlank(data.model)) {
                        true -> NextActionData.Continue(FooSamModel(model = FooModel().also { it.set("foo") }))
                        else -> NextActionData.Terminate(data.model)
                    }
                }
            }
        }
    }

}