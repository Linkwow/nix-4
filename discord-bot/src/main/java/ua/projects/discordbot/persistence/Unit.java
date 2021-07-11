package ua.projects.discordbot.persistence;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "units")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "units_generator")
    @SequenceGenerator(name = "units_generator", sequenceName = "units_sequence", allocationSize = 1)
    @Column(name = "id", updatable = false)
    private Short id;

    @NotNull
    @NotBlank
    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "faction")
    private Faction faction;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "category")
    private Category category;

    @Column(name = "cost")
    private short cost;

    @Column(name = "upkeep")
    private short upkeep;

    @Column(name = "health")
    private short health;

    @Column(name = "leadership")
    private short leadership;

    @Column(name = "speed")
    private short speed;

    @Column(name = "melee_attack")
    private short meleeAttack;

    @Column(name = "melee_defence")
    private short meleeDefence;

    @Column(name = "charge_bonus")
    private short chargeBonus;

    @Column(name = "missile_resistance")
    private short missileResistance;

    @Column(name = "magic_resistance")
    private short magicResistance;

    @Column(name = "armor_protection")
    private short armorProtection;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "weapon_type")
    private Weapon weaponType;

    @Column(name = "weapon_damage")
    private short weaponDamage;

    @Column(name = "armour_piercing_damage")
    private short armourPiercingDamage;

    @Column(name = "melee_interval")
    private short meleeInterval;

    @Column(name = "magical_attack")
    private short magicalAttack;

    @Column(name = "range")
    private short range;

    @Column(name = "unit_size")
    private short unitSize;

    @Column(name = "turns")
    private short turns;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "attributes_units",
            joinColumns = @JoinColumn(name = "unit_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_id", referencedColumnName = "id"))
    private Set<Attribute> attributeSet = new HashSet<>();

    public Unit() {
    }

    public Unit(String name, Faction faction, Category category, Weapon weaponType, short[] parameters) {
        this.name = name;
        this.faction = faction;
        this.category = category;
        this.weaponType = weaponType;
        this.cost = parameters[0];
        this.upkeep = parameters[1];
        this.health = parameters[2];
        this.leadership = parameters[3];
        this.speed = parameters[4];
        this.meleeAttack = parameters[5];
        this.meleeDefence = parameters[6];
        this.chargeBonus = parameters[7];
        this.missileResistance = parameters[8];
        this.magicResistance = parameters[9];
        this.armorProtection = parameters[10];
        this.weaponDamage = parameters[11];
        this.armourPiercingDamage = parameters[12];
        this.meleeInterval = parameters[13];
        this.magicalAttack = parameters[14];
        this.range = parameters[15];
        this.unitSize = parameters[16];
        this.turns = parameters[17];
    }

    public Short getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Faction getFaction() {
        return faction;
    }

    public Category getCategory() {
        return category;
    }

    public short getCost() {
        return cost;
    }

    public short getUpkeep() {
        return upkeep;
    }

    public short getHealth() {
        return health;
    }

    public short getLeadership() {
        return leadership;
    }

    public short getSpeed() {
        return speed;
    }

    public short getMeleeAttack() {
        return meleeAttack;
    }

    public short getMeleeDefence() {
        return meleeDefence;
    }

    public short getChargeBonus() {
        return chargeBonus;
    }

    public short getMissileResistance() {
        return missileResistance;
    }

    public short getMagicResistance() {
        return magicResistance;
    }

    public short getArmorProtection() {
        return armorProtection;
    }

    public Weapon getWeaponType() {
        return weaponType;
    }

    public short getWeaponDamage() {
        return weaponDamage;
    }

    public short getArmourPiercingDamage() {
        return armourPiercingDamage;
    }

    public short getMeleeInterval() {
        return meleeInterval;
    }

    public short getMagicalAttack() {
        return magicalAttack;
    }

    public short getRange() {
        return range;
    }

    public short getUnitSize() {
        return unitSize;
    }

    public short getTurns() {
        return turns;
    }

    public Set<Attribute> getAttributeSet() {
        return attributeSet;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
        faction.setUnitList(this);
    }

    public void setCategory(Category category) {
        this.category = category;
        category.setUnitList(this);
    }

    public void setCost(short cost) {
        this.cost = cost;
    }

    public void setUpkeep(short upkeep) {
        this.upkeep = upkeep;
    }

    public void setHealth(short health) {
        this.health = health;
    }

    public void setLeadership(short leadership) {
        this.leadership = leadership;
    }

    public void setSpeed(short speed) {
        this.speed = speed;
    }

    public void setMeleeAttack(short meleeAttack) {
        this.meleeAttack = meleeAttack;
    }

    public void setMeleeDefence(short meleeDefence) {
        this.meleeDefence = meleeDefence;
    }

    public void setChargeBonus(short chargeBonus) {
        this.chargeBonus = chargeBonus;
    }

    public void setMissileResistance(short missileResistance) {
        this.missileResistance = missileResistance;
    }

    public void setMagicResistance(short magicResistance) {
        this.magicResistance = magicResistance;
    }

    public void setArmorProtection(short armorProtection) {
        this.armorProtection = armorProtection;
    }

    public void setWeaponType(Weapon weaponType) {
        this.weaponType = weaponType;
        weaponType.setUnitList(this);
    }

    public void setWeaponDamage(short weaponDamage) {
        this.weaponDamage = weaponDamage;
    }

    public void setArmourPiercingDamage(short armourPiercingDamage) {
        this.armourPiercingDamage = armourPiercingDamage;
    }

    public void setMeleeInterval(short meleeInterval) {
        this.meleeInterval = meleeInterval;
    }

    public void setMagicalAttack(short magicalAttack) {
        this.magicalAttack = magicalAttack;
    }

    public void setRange(short range) {
        this.range = range;
    }

    public void setUnitSize(short unitSize) {
        this.unitSize = unitSize;
    }

    public void setTurns(short turns) {
        this.turns = turns;
    }

    public void setAttributeSet(Attribute attribute) {
        attributeSet.add(attribute);
        attribute.setUnitSet(this);
    }
}
