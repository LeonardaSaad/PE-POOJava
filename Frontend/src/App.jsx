import { useState } from "react";
import {
  Card,
  CardContent,
  CardFooter,
  CardHeader,
  CardTitle,
  CardDescription,
} from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { Button } from "@/components/ui/button";
import AlertDialogComponent from "./components/AlertDialogComponent";

export default function App() {
  const [funcionarioId, setFuncionarioId] = useState("");
  const [funcionarioName, setFuncionarioName] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log({
      "Funcionario Id": funcionarioId,
      "Funcionário Nome": funcionarioName,
    });
  };

  return (
    <>
      <h1 className="font-bold text-center m-10 text-2xl">CHD Ponto Digital</h1>
      <Tabs defaultValue="point-record" className="w-[400px]">
        <TabsList className="flex justify-center space-x-2">
          <TabsTrigger value="point-record">Registro de Ponto</TabsTrigger>
          <TabsTrigger value="register-user">
            Cadastro de Funcionários
          </TabsTrigger>
        </TabsList>
        <TabsContent value="point-record">
          <Card className="w-full max-w-md mx-auto">
            <CardHeader>
              <CardTitle>Registro de Ponto</CardTitle>
              <CardDescription>
                Insira seu ID para registrar o ponto
              </CardDescription>
            </CardHeader>
            <form onSubmit={handleSubmit}>
              <CardContent className="space-y-4">
                <div className="space-y-2">
                  <Label htmlFor="funcionarioId">ID do Funcionário</Label>
                  <Input
                    id="funcionarioId"
                    placeholder="Digite seu ID"
                    value={funcionarioId}
                    onChange={(e) => setFuncionarioId(e.target.value)}
                    required
                  />
                </div>
              </CardContent>
              <CardFooter>
                <AlertDialogComponent />
              </CardFooter>
            </form>
          </Card>
        </TabsContent>

        <TabsContent value="register-user">
          <Card className="w-full max-w-md mx-auto">
            <CardHeader>
              <CardTitle>Cadastro de Funcionários</CardTitle>
              <CardDescription>
                Insira nome completo do funcionário que deseja cadastrar.
              </CardDescription>
            </CardHeader>
            <form onSubmit={handleSubmit}>
              <CardContent className="space-y-4">
                <div className="space-y-2">
                  <Label htmlFor="nome">Nome do Funcionário</Label>
                  <Input
                    id="nome"
                    placeholder="Digite seu nome completo"
                    value={funcionarioName}
                    onChange={(e) => setFuncionarioName(e.target.value)}
                    required
                  />
                </div>
              </CardContent>
              <CardFooter>
                <Button type="submit" className="w-full">
                  Cadastrar funcionário
                </Button>
              </CardFooter>
            </form>
          </Card>
        </TabsContent>
      </Tabs>
    </>
  );
}
