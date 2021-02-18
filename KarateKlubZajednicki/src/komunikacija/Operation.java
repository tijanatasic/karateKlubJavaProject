/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import java.io.Serializable;

/**
 *
 * @author Folio1040
 */
public enum Operation implements Serializable{
    ZAPAMTI_CLANA,
    PRONADJI_CLANOVE,
    UCITAJ_IZABRANOG_CLANA,
    IZBRISI_CLANA,
    UCITAJ_CLANOVE,
    ZAPAMTI_POLAGANJE,
    ZAPAMTI_TAKMICARA,
    UCITAJ_TAKMICARE,
    ZAPAMTI_TAKMICENJE,
    ZAPAMTI_TIM,
    PRONADJI_TIM,
    UCITAJ_TIMOVE,
    IZMENI_CLANOVE,
    IZBRISI_TIM,
    IZMENI_TIMOVE,
    IZMENI_CLANA,
    UCITAJ_TAKMICENJA,
    ZAPAMTI_REZULTAT_TAKMICENJA,
    UCITAJ_POLAGANJA,
    ZAPAMTI_REZULTAT_POLAGANJA,
    OSVEZI_TAKMICARE,
    UCITAJ_REZULTATE_POLAGANJA,
    UCITAJ_STATISTIKE_TAKMICENJA,
    IZMENI_TAKMICARE,
    IZMENI_TAKMICARA,
    OBRISI_TAKMICARA,
    EXIT
}
