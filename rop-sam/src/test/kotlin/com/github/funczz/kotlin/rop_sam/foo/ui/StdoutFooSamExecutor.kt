package io.kotlintest.provided.com.github.funczz.kotlin.rop_sam.foo.ui

import com.github.funczz.kotlin.rop.result.RopResult
import com.github.funczz.kotlin.rop_sam.ISamExecutor
import io.kotlintest.provided.com.github.funczz.kotlin.rop_sam.foo.core.FooData
import io.kotlintest.provided.com.github.funczz.kotlin.rop_sam.foo.core.FooSamModel
import io.kotlintest.provided.com.github.funczz.kotlin.rop_sam.foo.core.FooSamModelPresent
import io.kotlintest.provided.com.github.funczz.kotlin.rop_sam.foo.core.FooSamStateNextAction

object StdoutFooSamExecutor : ISamExecutor<
        FooData,
        FooSamModel,
        Unit,
        String> {

    override fun samPresent(): (FooData) -> RopResult<FooSamModel> = FooSamModelPresent::present

    override fun samNextAction(): (FooSamModel) -> RopResult<FooSamModel> = FooSamStateNextAction::nextAction

    override fun samRepresentation(): (FooSamModel, Unit) -> RopResult<String> =
        StdoutFooSamStateRepresentation::representation


}