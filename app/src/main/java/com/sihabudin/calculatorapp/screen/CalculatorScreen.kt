package com.sihabudin.calculatorapp.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sihabudin.calculatorapp.component.ButtonCalculator
import com.sihabudin.calculatorapp.core.Calculator
import com.sihabudin.calculatorapp.ui.theme.Blue20
import com.sihabudin.calculatorapp.ui.theme.Blue60
import com.sihabudin.calculatorapp.ui.theme.NetralVariant80
import com.sihabudin.calculatorapp.ui.theme.Red
import com.sihabudin.calculatorapp.viewmodel.CalculatorViewModel

@Composable
fun CalculatorScreen(
    viewModel: CalculatorViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .align(Alignment.BottomCenter)
                .padding(all = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            DisplayCalculate(viewModel = viewModel)
            OptionSplitNumber(viewModel = viewModel)
            RowOne(viewModel = viewModel)
            RowTwo(viewModel = viewModel)
            RowThree(viewModel = viewModel)
            RowFour(viewModel = viewModel)
            RowFive(viewModel = viewModel)
        }
    }
}


@Composable
fun DisplayCalculate(
    viewModel: CalculatorViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = viewModel.calculatorState.operationDisplay,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            fontWeight = FontWeight.Light,
            fontSize = 32.sp,
            color = NetralVariant80,
            maxLines = 2
        )
        Text(
            text = viewModel.calculatorState.resultDisplay,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            fontWeight = FontWeight.Light,
            fontSize = 32.sp,
            color = Color.Black,
            maxLines = 10
        )

        if (viewModel.calculatorState.operation == OperationState.SplitRemainder || viewModel.calculatorState.operation == OperationState.SplitEqual) {
            Text(
                text = viewModel.calculatorState.hintInformation,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                fontWeight = FontWeight.Light,
                fontSize = 16.sp,
                color = Color.Black,
                maxLines = 2
            )
        }
    }
}


@Composable
fun OptionSplitNumber(
    viewModel: CalculatorViewModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    )
    {
        ButtonCalculator(
            textButton = "Split Equal",
            colorBackground = Blue20,
            sizeText = 16.sp,
            onClick = { viewModel.setOperation(OperationState.SplitEqual) },
            modifier = Modifier
                .aspectRatio(2f)
                .weight(2f)
        )
        ButtonCalculator(
            textButton = "Split Remainder",
            colorBackground = Blue20,
            sizeText = 16.sp,
            onClick = { viewModel.setOperation(OperationState.SplitRemainder) },
            modifier = Modifier
                .aspectRatio(2f)
                .weight(2f)
        )
    }

}

@Composable
fun RowOne(
    viewModel: CalculatorViewModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    )
    {
        ButtonCalculator(
            textButton = "C",
            colorBackground = Red,
            onClick = { viewModel.clearState() },
            modifier = Modifier
                .aspectRatio(2f)
                .weight(2f)
        )
        ButtonCalculator(
            textButton = "DEL",
            colorBackground = Blue20,
            sizeText = 16.sp,
            onClick = { viewModel.deleteInput() },
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        )
        ButtonCalculator(
            textButton = "/",
            colorBackground = Blue20,
            onClick = { viewModel.setOperation(OperationState.Divide) },
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        )
    }
}

@Composable
fun RowTwo(
    viewModel: CalculatorViewModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    )
    {
        ButtonCalculator(
            textButton = "7",
            colorBackground = Blue60,
            onClick = { viewModel.inputNumber("7") },
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        )
        ButtonCalculator(
            textButton = "8",
            colorBackground = Blue60,
            onClick = { viewModel.inputNumber("8") },
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        )
        ButtonCalculator(
            textButton = "9",
            colorBackground = Blue60,
            onClick = { viewModel.inputNumber("9") },
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        )
        ButtonCalculator(
            textButton = "X",
            colorBackground = Blue20,
            onClick = { viewModel.setOperation(OperationState.Multiply) },
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        )
    }
}

@Composable
fun RowThree(
    viewModel: CalculatorViewModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    )
    {
        ButtonCalculator(
            textButton = "4",
            colorBackground = Blue60,
            onClick = { viewModel.inputNumber("4") },
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        )
        ButtonCalculator(
            textButton = "5",
            colorBackground = Blue60,
            onClick = { viewModel.inputNumber("5") },
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        )
        ButtonCalculator(
            textButton = "6",
            colorBackground = Blue60,
            onClick = { viewModel.inputNumber("6") },
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        )
        ButtonCalculator(
            textButton = "-",
            colorBackground = Blue20,
            onClick = { viewModel.setOperation(OperationState.Subtract) },
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        )
    }
}

@Composable
fun RowFour(
    viewModel: CalculatorViewModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    )
    {
        ButtonCalculator(
            textButton = "1",
            colorBackground = Blue60,
            onClick = { viewModel.inputNumber("1") },
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        )
        ButtonCalculator(
            textButton = "2",
            colorBackground = Blue60,
            onClick = { viewModel.inputNumber("2") },
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        )
        ButtonCalculator(
            textButton = "3",
            colorBackground = Blue60,
            onClick = { viewModel.inputNumber("3") },
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        )
        ButtonCalculator(
            textButton = "+",
            colorBackground = Blue20,
            onClick = { viewModel.setOperation(OperationState.Add) },
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        )
    }
}


@Composable
fun RowFive(
    viewModel: CalculatorViewModel,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    )
    {

        ButtonCalculator(
            textButton = "00",
            colorBackground = Blue60,
            onClick = { viewModel.inputNumber("00") },
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        )
        ButtonCalculator(
            textButton = "0",
            colorBackground = Blue60,
            onClick = { viewModel.inputNumber("0") },
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        )
        ButtonCalculator(
            textButton = ".",
            colorBackground = Blue20,
            onClick = { viewModel.inputDecimal() },
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        )
        ButtonCalculator(
            textButton = "=",
            colorBackground = Blue20,
            onClick = { viewModel.calculate() },
            modifier = Modifier
                .aspectRatio(1f)
                .weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorScreenPreview() {
    CalculatorScreen(viewModel = CalculatorViewModel(calculator = Calculator()))
}