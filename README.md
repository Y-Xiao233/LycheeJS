``` javascript
ServerEvents.recipes(event =>{
    let post = PostBuilder.create()
    let contextual = ContextualBuilder.create()
    event.recipes.lychee.block_clicking(
        [
            SizedIngredientWrapper.of("minecraft:gold_block")
        ],
        BlockPredicateWrapper.block("minecraft:iron_block"),
        [
            post.withChance(0.6).dropItem("minecraft:gold_block")
        ]
    ).ghost(true)

    event.recipes.lychee.item_inside(
        [
            SizedIngredientWrapper.of("minecraft:gravel")
        ],
        BlockPredicateWrapper.block("minecraft:water"),
        [
            post.dropItem("minecraft:sand")
        ],
        contextual.chance(0.5).build()
    )

    event.recipes.lychee.anvil_crafting(
        [
            Ingredient.of("minecraft:gravel"),
            Ingredient.of("minecraft:cobblestone")
        ],
        Item.of("minecraft:sand"),
        1,1
    )

    event.recipes.lychee.block_crushing(
        BlockPredicateWrapper.ANVIL,
        BlockPredicateWrapper.ANY,
        SizedIngredientWrapper.of("minecraft:cobblestone"),
        [
            post.withChance(0.6).dropItem("minecraft:gravel")
        ]
    )

    event.recipes.lychee.block_exploding(
        BlockPredicateWrapper.block("minecraft:grass_block"),
        [
            post.withChance(0.6).dropItem("minecraft:gravel")
        ]
    )

    event.recipes.lychee.block_interacting(
        [
            SizedIngredientWrapper.of("minecraft:gold_ingot")
        ],
        BlockPredicateWrapper.block("minecraft:iron_block"),
        post.withChance(0.6).consumeAndPlace("minecraft:gold_block")
    )

    event.recipes.lychee.dripstone_dripping(
        BlockPredicateWrapper.block("minecraft:water"),
        BlockPredicateWrapper.block("minecraft:dirt"),
        post.withChance(0.6).consumeAndPlace("minecraft:clay")
    )

    event.recipes.lychee.item_burning(
        SizedIngredientWrapper.of("#minecraft:logs"),
        [
            post.dropItem("minecraft:coal")
        ]
    )

    event.recipes.lychee.item_exploding(
        [
            SizedIngredientWrapper.of("minecraft:cobblestone")
        ],
        [
            post.dropItem("minecraft:gravel")
        ]
    )

    event.recipes.lychee.lightning_channeling(
        [
            SizedIngredientWrapper.of("minecraft:cobblestone")
        ],
        [
            post.dropItem("minecraft:gravel")
        ]
    )

    event.recipes.lychee.random_block_ticking(
        BlockPredicateWrapper.block("minecraft:iron_block"),
        [
            post.dropItem("minecraft:dirt")
        ]
    )
})



```