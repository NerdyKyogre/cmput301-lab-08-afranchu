package com.example.lab_8


import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class WizardTest {
    private lateinit var evilWizard: Wizard

    // Set up a new wizard before every single test
    @Before
    fun setUp() {
        evilWizard = Wizard("Evil Wizard", 30, 10)
    }

    @Test
    fun castSpell_explosion_successful() {
        val damageDealt = evilWizard.castSpell("Explosion")

        assertEquals(30, damageDealt) // spellPower of 10 * 3 = 30

        assertEquals(20, evilWizard.mana) // mana of 30 - 10 = 20
    }

    @Test
    fun castSpell_explosion_unsuccessful() {
        evilWizard.mana = 5 // Evil Wizard does not have enough mana to cast "Explosion"
        val damageDealt = evilWizard.castSpell("Explosion")

        assertEquals(0, damageDealt)

        assertEquals(5, evilWizard.mana) // mana still is 5
    }

    @Test
    fun restoreMana_amountAddedExceedsOneHundred_manaSetToOneHundred() {
        evilWizard.restoreMana(80) // 30 + 80 = 110, so mana should be set to 100

        assertEquals(100, evilWizard.mana)
    }

    @Test
    fun restoreMana_amountAddedDoesNotExceedOneHundred_manaIncreasesCorrectly() {
        evilWizard.restoreMana(40) // 30 + 40 = 70, so mana should be set to 70

        assertEquals(70, evilWizard.mana)
    }

    @Test
    fun train_amountManaOver5_spellPowerDecreasesAndManaIncreasesCorrectly() {
        evilWizard.mana = 10
        evilWizard.spellPower = 10
        evilWizard.train()
        assertEquals(5, evilWizard.mana) // training consumed 5 mana from 10 = 5
        assertEquals(11, evilWizard.spellPower) // training increased spellPower by 1 from 10 = 11
    }
    @Test
    fun train_amountManaUnder5_DoesNothing() {
        evilWizard.mana = 2
        evilWizard.spellPower = 10
        evilWizard.train()
        assertEquals(2, evilWizard.mana) // 2 < 5 so mana is not consumed = 2
        assertEquals(10, evilWizard.spellPower) // we didn't increase in spell power so still 10
    }
}