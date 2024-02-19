package com.example.practicafinal_victorhernandez

// Mapas.kt
class Mapas {

    // Creacion de lista que alberga toda la informacion sobre las series
    val informacionSeries = listOf(
        mapOf(
            // Titulo de una de las series de la lista
            "titulo" to "La Casa de Papel",
            // Imagen de una de las series de la lista
            "imagen" to "imagenlacasadepapel",
            // Sinopsis de una de las series de la lista
            "sinopsis" to "Un misterioso hombre conocido como «el Profesor» " +
                    "ha pasado toda su vida planeando el mayor de los atracos de " +
                    "la historia: entrar en la Fábrica Nacional de Moneda y Timbre" +
                    "e imprimir 2400 millones de euros. Para llevar a cabo este " +
                    "ambicioso plan, el Profesor recluta a un equipo de ocho " +
                    "personas con ciertas habilidades y que no tienen nada que " +
                    "perder. Estos, junto al Profesor, planean cada paso del " +
                    "atraco durante cinco meses. Este equipo, con nombres de " +
                    "diferentes ciudades del mundo, requiere de 11 días de " +
                    "reclusión en la Fábrica, durante los cuales tiene que lidiar " +
                    "con las fuerzas de élite de la policía y 67 rehenes."
            // Todas cuentan con lo mismo, por tanto cada mapa contiene un titulo, una imagen y una sinopsis
        ),
        mapOf(
            "titulo" to "Juego de Tronos",
            "imagen" to "imagenjuegodetronos",
            "sinopsis" to "La primera temporada comienza quince años después de " +
                    "la guerra civil conocida como la «rebelión de Robert», con " +
                    "la cual Robert Baratheon expulsó del Trono de Hierro a los " +
                    "Targaryen y se proclamó gobernante de Poniente. Tiempo " +
                    "después, y tras la repentina muerte de la Mano del Rey, Jon " +
                    "Arryn, Robert invita a su amigo Eddard «Ned» Stark —Lord de " +
                    "Invernalia— a asumir el oficio vacante."
        ),
        mapOf(
            "titulo" to "The Walking Dead",
            "imagen" to "imagenthewalkingdead",
            "sinopsis" to "The Walking Dead tiene lugar antes del inicio de un " +
                    "apocalipsis zombi mundial. Los zombis, coloquialmente " +
                    "llamados «Maricrons», se arrastran hacia los humanos vivos " +
                    "y otras criaturas para usarlos; se sienten atraídos por " +
                    "ellos, como los disparos, y por diferentes aromas, por " +
                    "ejemplo humanos. Aunque inicialmente parece que solo los " +
                    "humanos que son mordidos o arañados por los maricrons pueden " +
                    "convertirse en otros maricrons, se revela al principio de " +
                    "la serie que todos los humanos vivos portan el patógeno " +
                    "responsable de la mutación."
        ),
        mapOf(
            "titulo" to "Stranger Things",
            "imagen" to "imagenstrangerthings",
            "sinopsis" to "La historia arranca durante la década de los 80, en el " +
                    "pueblo ficticio de Hawkins, Indiana, El cercano Laboratorio " +
                    "Nacional del pueblo aparentemente realiza investigaciones " +
                    "científicas para el Departamento de Energía de los Estados " +
                    "Unidos, pero experimenta en secreto con lo paranormal y lo " +
                    "sobrenatural, a veces con sujetos de prueba humanos. Sin darse " +
                    "cuenta, han creado un portal a una dimensión alternativa a la " +
                    "que se refieren como Upside Down, cuya presencia comienza a " +
                    "afectar a los residentes de Hawkins de manera calamitosa."
        ),
        mapOf(
            "titulo" to "Los Simpson",
            "imagen" to "imagenlossimpson",
            "sinopsis" to "Los Simpson —en inglés: The Simpsons— es una serie " +
                    "estadounidense de comedia en formato de animación, creada " +
                    "por Matt Groening para Fox Broadcasting Company y emitida en " +
                    "varios países del mundo. La serie es una sátira de la " +
                    "sociedad estadounidense que narra la vida y el día a día " +
                    "de una familia de clase media de ese país —cuyos miembros " +
                    "son Homer, Marge, Bart, Lisa y Maggie Simpson— que vive en " +
                    "un pueblo ficticio llamado Springfield."
        )
    )

    // Creacion de lista que alberga toda la informacion sobre las peliculas
    val informacionPeliculas = listOf(
        mapOf(
            "titulo" to "Interestellar",
            "imagen" to "imageninterestellar",
            "sinopsis" to "En 2067, la destrucción de las cosechas en la Tierra ha " +
                    "hecho que la agricultura sea cada vez más difícil y se vea " +
                    "amenazada la supervivencia de la humanidad. Joseph Cooper, " +
                    "viudo, exingeniero y piloto de la NASA, dirige una granja " +
                    "con su suegro Donald, su hijo Tom y su hija Murph, quien " +
                    "cree que su habitación está embrujada por un poltergeist. " +
                    "Cuando aparecen inexplicablemente extraños patrones de " +
                    "polvo en el suelo de la habitación de Murph, Cooper se da " +
                    "cuenta de que la gravedad está detrás de su formación, no " +
                    "un «fantasma». Interpreta el patrón como un conjunto de " +
                    "coordenadas geográficas formadas en código binario. Cooper " +
                    "y Murph siguen las coordenadas a una instalación secreta " +
                    "de la NASA, donde se encuentran con el exprofesor de " +
                    "Cooper, el doctor Brand."
        ),
        mapOf(
            "titulo" to "Gladiator",
            "imagen" to "imagengladiator",
            "sinopsis" to "Gladiator (llamada Gladiador en Hispanoamérica) es " +
                    "una película épica del género péplum y acción del año 2000 " +
                    "dirigida por Ridley Scott y protagonizada por Russell " +
                    "Crowe, Joaquin Phoenix y Connie Nielsen. Crowe interpreta " +
                    "a Máximo Décimo Meridio, un leal general hispano del " +
                    "ejército del Imperio romano que es traicionado por Cómodo, " +
                    "el ambicioso hijo del emperador Marco Aurelio, quien ha " +
                    "asesinado a su padre y se ha hecho con el trono. Forzado " +
                    "a convertirse en esclavo, Máximo triunfa como gladiador " +
                    "mientras anhela vengar la muerte de su familia y la del " +
                    "emperador."
        ),
        mapOf(
            "titulo" to "Titanic",
            "imagen" to "imagentitanic",
            "sinopsis" to "Titanic es una película estadounidense de 1997, " +
                    "dramática y de catástrofe, dirigida y escrita por James " +
                    "Cameron y protagonizada por Leonardo DiCaprio, Kate " +
                    "Winslet, Billy Zane, Kathy Bates, Gloria Stuart y Bill " +
                    "Paxton. La trama, una epopeya romántica, relata la " +
                    "relación de Jack Dawson y Rose DeWitt Bukater, dos " +
                    "jóvenes que se conocen y se enamoran a bordo del " +
                    "transatlántico RMS Titanic en su viaje inaugural desde " +
                    "Southampton (Inglaterra) a Nueva York (Estados Unidos) " +
                    "en abril de 1912. Pertenecientes a diferentes clases " +
                    "sociales, intentan salir adelante pese a las adversidades " +
                    "que los separarían de forma definitiva, entre ellas el " +
                    "prometido de Rose, Caledon «Cal» Hockley (un adinerado " +
                    "del cual ella no está enamorada, pero su madre la ha " +
                    "obligado a permanecer con él para garantizar un futuro " +
                    "económico próspero) y el hundimiento del barco tras " +
                    "chocar con un iceberg."
        ),
        mapOf(
            "titulo" to "WALL·E",
            "imagen" to "imagenwalle",
            "sinopsis" to "En el siglo XXIX, específicamente en el año 2805, " +
                    "el consumismo desenfrenado, la codicia empresarial y la " +
                    "negligencia ambiental han convertido al planeta Tierra " +
                    "en un páramo lleno de basura; la humanidad no se " +
                    "encuentra por ningún lado y ha sido evacuada por la " +
                    "megacorporación Buy-n-Large (BnL) en gigantes naves " +
                    "generacionales siete siglos antes. De todos los robots " +
                    "compactadores de basura Waste Allocation Load Lifter - " +
                    "Earth class o WALL•E, solo uno queda. Un día, la rutina " +
                    "de WALL•E de comprimir basura y recolectar objetos " +
                    "interesantes se rompe con la llegada de una nave no " +
                    "tripulada que lleva una robot llamada Evaluador de " +
                    "Vegetación Alienígena o EVA, enviada para escanear el " +
                    "planeta en busca de vida sustanciable para los humanos. " +
                    "WALL•E se enamora de ella, y los dos comienzan a " +
                    "conectarse hasta que EVA entra en modo de espera cuando " +
                    "WALL•E le muestra su hallazgo más reciente: una planta " +
                    "viva. Luego, la nave recolecta a EVA y la planta y, con " +
                    "WALL•E aferrado, regresa a su nave nodriza, el Axioma."
        ),
        mapOf(
            "titulo" to "Intocable",
            "imagen" to "imagenintocable",
            "sinopsis" to "La película narra la relación entre dos personajes " +
                    "totalmente opuestos y procedentes de entornos diferentes. " +
                    "Uno, Driss, un joven de origen senegalés, vive en un " +
                    "barrio obrero de París, con antecedentes penales, " +
                    "vitalista, descarado, divertido e irreflexivo; el otro, " +
                    "Phillippe, un rico y tetrapléjico ya mayor, culto y muy " +
                    "poco espontáneo, que necesita un ayudante personal. Driss " +
                    "acude a la entrevista esperando ser rechazado y con la " +
                    "sola pretensión de poder sellar sus papeles para cobrar " +
                    "el seguro de desempleo. Sin embargo, y para su sorpresa, " +
                    "es contratado, pese a su descaro y a no tener ninguna " +
                    "formación profesional. Los motivos de Phillippe, harto de " +
                    "lidiar con cuidadores que sentían piedad de él, fue el " +
                    "ver que no le tenía compasión alguna y el reto de " +
                    "conseguir que el rebelde joven fuera capaz de realizar " +
                    "un trabajo y adaptarse a unos estrictos horarios y " +
                    "rutinas responsablemente."
        )
    )
}