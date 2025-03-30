# Fuzzy Logic en Java

## ¿Cómo interpretar el archivo de reglas?

### **Estructura del archivo FCL**:

El archivo contiene tres bloques principales:

1.  **Definición de las variables de entrada (FUZZIFY)**
    
2.  **Definición de la variable de salida (DEFUZZIFY)**
    
3.  **Definición de las reglas difusas (RULEBLOCK)**
    

### **1. `VAR_INPUT` y `VAR_OUTPUT`**:

```
VAR_INPUT
    budget : REAL;
    rating : REAL;
END_VAR

VAR_OUTPUT
    recommendation : REAL;
END_VAR
```

-   **`budget`**: Esta es una variable de entrada que representa el presupuesto.
    
-   **`rating`**: Esta es otra variable de entrada que representa la valoración (probablemente de un servicio o producto).
    
-   **`recommendation`**: Esta es la variable de salida, que contendrá la recomendación en función de las reglas definidas.


### **2. Definición de las funciones de membresía para las entradas** (`FUZZIFY`):

##### **FUZZIFY `budget`**:
```
FUZZIFY budget
    TERM budget_low := (0, 1) (500, 0);
    TERM budget_medium := (400, 0) (1000, 1) (1600, 0);
    TERM budget_high := (1500, 0) (3000, 1);
END_FUZZIFY
```

Esto indica que se está definiendo cómo la variable `budget` (presupuesto) será interpretada en el sistema difuso. En otras palabras, cómo se va a transformar el valor numérico que se ingresa (por ejemplo, un valor de 700) en un conjunto difuso de términos (por ejemplo, bajo, medio, alto).

#### **TERM `budget_low := (0, 1) (500, 0)`**:

Esta es la primera **función de membresía** (membership function) para el término **`budget_low`** (presupuesto bajo). Los números en los paréntesis representan puntos en el espacio de entrada (en este caso, el presupuesto) y su grado de pertenencia al conjunto difuso **bajo**.

-   `(0, 1)` significa que cuando el presupuesto es 0, el grado de pertenencia es **1** (es completamente bajo).
    
-   `(500, 0)` significa que cuando el presupuesto es 500, el grado de pertenencia es **0** (ya no es bajo).
    

Esto indica que **`budget_low`** es alto cuando el presupuesto es 0, y disminuye hasta ser 0 cuando el presupuesto alcanza 500.

#### **TERM `budget_medium := (400, 0) (1000, 1) (1600, 0)`**:

Esta es la **función de membresía** para el término **`budget_medium`** (presupuesto medio). Esta función describe un presupuesto que comienza en 400 con un grado de pertenencia de 0 (es decir, no es medio), alcanza su máximo grado de pertenencia de 1 cuando el presupuesto es 1000, y luego vuelve a 0 cuando el presupuesto llega a 1600.

-   `(400, 0)` significa que cuando el presupuesto es 400, el grado de pertenencia es **0**.
    
-   `(1000, 1)` significa que cuando el presupuesto es 1000, el grado de pertenencia es **1** (es completamente medio).
    
-   `(1600, 0)` significa que cuando el presupuesto es 1600, el grado de pertenencia es **0** (ya no es medio).
    

Este término **`budget_medium`** es más fuerte alrededor del valor de 1000, y va disminuyendo a medida que nos alejamos de ese valor.

#### **TERM `budget_high := (1500, 0) (3000, 1)`**:

Finalmente, esta es la **función de membresía** para el término **`budget_high`** (presupuesto alto).

-   `(1500, 0)` significa que cuando el presupuesto es 1500, el grado de pertenencia es **0** (no es alto).
    
-   `(3000, 1)` significa que cuando el presupuesto es 3000, el grado de pertenencia es **1** (es completamente alto).
    

Este término **`budget_high`** comienza en 1500 y se vuelve más fuerte a medida que el presupuesto se acerca a 3000.

### #**`END_FUZZIFY`**:

Esto marca el final de la definición de los términos difusos para la variable `budget`.

### **¿Qué significa esto?**

La sección `FUZZIFY` define **cómo se mapea un valor numérico de la variable `budget`** a un conjunto difuso de categorías (bajo, medio, alto). La lógica difusa no tiene una frontera clara entre estas categorías, por lo que un presupuesto de 700, por ejemplo, podría tener un grado de pertenencia a "bajo" de 0.5 y a "medio" de 0.5, lo que significa que podría considerarse tanto bajo como medio en cierta medida.

**Resumen de los términos:**

-   **`budget_low`**: Se asigna a presupuestos cercanos a 0, y va disminuyendo a medida que el presupuesto aumenta.
    
-   **`budget_medium`**: Se asigna al presupuesto alrededor de 1000, con grados de pertenencia que aumentan hasta 1 en ese punto y luego disminuyen.
    
-   **`budget_high`**: Se asigna a presupuestos cercanos a 3000.
    

### **Visualización**:

Si dibujáramos las funciones de membresía, veríamos algo como esto:

-   **`budget_low`**: una curva que empieza en 1 para 0 y baja hasta 0 en 500.
    
-   **`budget_medium`**: una curva triangular que tiene un valor de 1 en 1000, y baja a 0 en 400 y 1600.
    
-   **`budget_high`**: una curva que empieza en 0 en 1500 y sube a 1 en 3000.
    

Esto permite que los valores de `budget` sean interpretados de manera flexible, sin ser exactos, pero aún así útiles para decisiones de lógica difusa.

#### **FUZZIFY `rating`**:

```
FUZZIFY rating
    TERM rating_bad := (0, 1) (3, 0);
    TERM rating_regular := (2, 0) (5, 1) (8, 0);
    TERM rating_good := (7, 0) (10, 1);
END_FUZZIFY
```

-   **`rating_bad`**: Función de membresía para "valoración mala". El valor es 1 cuando la valoración es 0 y disminuye a 0 cuando la valoración llega a 3.
    
-   **`rating_regular`**: Función de membresía para "valoración regular". Comienza en 0 en 2, aumenta a 1 en 5, y vuelve a 0 en 8.
    
-   **`rating_good`**: Función de membresía para "valoración buena". Comienza en 0 en 7 y llega a 1 cuando la valoración es 10.

### **3. Definición de la función de membresía para la salida** (`DEFUZZIFY`):

```
DEFUZZIFY recommendation
    TERM rec_low := (0, 1) (3, 0);
    TERM rec_medium := (2, 0) (5, 1) (8, 0);
    TERM rec_high := (7, 0) (10, 1);
    METHOD : COG;
END_DEFUZZIFY
```

-   **`rec_low`**: Función de membresía para "recomendación baja", similar a las de entrada, comienza en 1 cuando la recomendación es 0 y baja a 0 cuando la recomendación llega a 3.
    
-   **`rec_medium`**: Función de membresía para "recomendación media", similar a las de entrada, con el valor de pertenencia máximo de 1 cuando la recomendación es 5.
    
-   **`rec_high`**: Función de membresía para "recomendación alta", que llega a 1 cuando la recomendación es 10.
    

El **método de defuzzificación** usado es **COG (Center of Gravity)**, lo que significa que la salida será calculada como el "centro de gravedad" de los valores de pertenencia.

**4. Definición de las reglas difusas** (`RULEBLOCK`):

```
RULEBLOCK Reglas
    RULE 1: IF budget IS budget_low AND rating IS rating_bad THEN recommendation IS rec_low;
    RULE 2: IF budget IS budget_low AND rating IS rating_regular THEN recommendation IS rec_medium;
    RULE 3: IF budget IS budget_medium AND rating IS rating_good THEN recommendation IS rec_high;
    RULE 4: IF budget IS budget_high THEN recommendation IS rec_high;
END_RULEBLOCK
```

-   **`RULE 1`**: Si el **presupuesto** es bajo (**`budget_low`**) y la **valoración** es mala (**`rating_bad`**), entonces la **recomendación** será baja (**`rec_low`**).
    
-   **`RULE 2`**: Si el **presupuesto** es bajo (**`budget_low`**) y la **valoración** es regular (**`rating_regular`**), entonces la **recomendación** será media (**`rec_medium`**).
    
-   **`RULE 3`**: Si el **presupuesto** es medio (**`budget_medium`**) y la **valoración** es buena (**`rating_good`**), entonces la **recomendación** será alta (**`rec_high`**).
    
-   **`RULE 4`**: Si el **presupuesto** es alto (**`budget_high`**), entonces la **recomendación** será alta (**`rec_high`**), sin importar la valoración.