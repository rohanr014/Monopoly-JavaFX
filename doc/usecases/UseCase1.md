### Use case 1 - 
A player lands on a property and can?t afford the forced fees so they sell properties to the bank.
- Land on space
- app.controller.Engine.Space.onLand(app.controller.Engine.Player p) ? will attempt to move money from current player to owner of property.
- Check app.controller.Engine.Player p's funds
- If they are lower than the rent, app.controller.Engine.Player p is given the option to sell (or mortgage) one or more of their properties
