program MorseCoding;

function checkUserInput(userString: string): boolean;
var
  valid: boolean;
  count: integer;
begin
  valid := true;
  for count := 1 to length(userString) do
    if ((ord(userString[count]) < 65) or (ord(userString[count]) > 90)) and (userString[count] <> 00) then
      valid := false;
  checkUserInput := valid;
end.