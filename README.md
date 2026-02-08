``` javascript
ServerEvents.recipes(event =>{
    let post = PostBuilder.create()
    let contextual = ContextualBuilder.create()
    event.recipes.lychee.block_clicking(
        [
            SizedIngredient.of("minecraft:gold_block")
        ],
        BlockPredicate.block("minecraft:iron_block"),
        [
            post.withChance(0.6).dropItem("minecraft:gold_block")
        ]
    ).ghost(true)

    event.recipes.lychee.item_inside(
        [
            SizedIngredient.of("minecraft:gravel")
        ],
        BlockPredicate.block("minecraft:water"),
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
        BlockPredicate.ANVIL,
        BlockPredicate.ANY,
        SizedIngredient.of("minecraft:cobblestone"),
        [
            post.withChance(0.6).dropItem("minecraft:gravel")
        ]
    )

    event.recipes.lychee.block_exploding(
        BlockPredicate.block("minecraft:grass_block"),
        [
            post.withChance(0.6).dropItem("minecraft:gravel")
        ]
    )

    event.recipes.lychee.block_interacting(
        [
            SizedIngredient.of("minecraft:gold_ingot")
        ],
        BlockPredicate.block("minecraft:iron_block"),
        post.withChance(0.6).consumeAndPlace("minecraft:gold_block")
    )

    event.recipes.lychee.dripstone_dripping(
        BlockPredicate.block("minecraft:water"),
        BlockPredicate.block("minecraft:dirt"),
        post.withChance(0.6).consumeAndPlace("minecraft:clay")
    )

    event.recipes.lychee.item_burning(
        SizedIngredient.of("#minecraft:logs"),
        [
            post.dropItem("minecraft:coal")
        ]
    )

    event.recipes.lychee.item_exploding(
        [
            SizedIngredient.of("minecraft:cobblestone")
        ],
        [
            post.dropItem("minecraft:gravel")
        ]
    )

    event.recipes.lychee.lightning_channeling(
        [
            SizedIngredient.of("minecraft:cobblestone")
        ],
        [
            post.dropItem("minecraft:gravel")
        ]
    )

    event.recipes.lychee.random_block_ticking(
        BlockPredicate.block("minecraft:iron_block"),
        [
            post.dropItem("minecraft:dirt")
        ]
    )
})



```