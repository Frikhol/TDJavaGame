public abstract class EnemyEffect extends Effect{
    //название для визуальных эффектов
    //сила воздействия эффекта
    //time время действия (-1 для неограниченного)
    //tag (список внизу) для определения на какой парамертр аоздейтвовать
    //source источник - эффекты из одного источника и с одним тэгом не могут весеть на одном объекте (старый удаляятся)
    //enemy на кого вешаем эффект
    EnemyEffect(String name, float power, float time, String tag, String source, Enemy enemy) {
        super(name, power, time, tag,source, enemy);
    }

    public static class EffectSlowMotion extends EnemyEffect{
        EffectSlowMotion(float power, float time, String source, Enemy enemy) {
            super("slow", power, time, "move_speed",source, enemy);
        }
    }
    /////////////////////////tags///////////////////////////
    //move_speed    // замедление/ускорение/стан
    //time_damage   // урон(яд/ожог)
    //stamina       // запас здоровья
    //resist        // уменьшение/увеличение брони
}
